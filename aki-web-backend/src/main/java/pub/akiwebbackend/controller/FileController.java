package pub.akiwebbackend.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.net.url.UrlBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;
import pub.akiwebbackend.exception.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author cym
 * @date 2024/4/9
 */
@Tag(name = "文件上传接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.port}")
    private int port;

    /**
     * 文件上传
     * @param file 文件
     * @return 文件访问链接
     */
    @Operation(summary = "文件上传")
    @PostMapping("/upload")
    @RequestBody(content = {@Content(
            mediaType = "multipart/form-data",
            schema = @Schema(type = "object"),
            schemaProperties = {
                    @SchemaProperty(
                            name = "file",
                            schema = @Schema(type = "string",format = "binary")
                    )}
    )})
    public R uploadFile(MultipartFile file) {
        // 1. file校验
        if (file == null) throw new BusinessException(ErrorCode.USER_ERROR_A0400);
        long fileSize = file.getSize();
        String fileSuffix = FileUtil.getSuffix(file.getOriginalFilename());

        if (fileSize > 1024 * 1024L) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0702, "文件大小不能超过1M");
        }
        if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0701, "文件类型错误");
        }

        // 2. file重命名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + "." + fileSuffix;

        // 3. file保存/上传
        // 文件目录：根据业务、用户来划分
        // todo 打jar包之后还是这个路径吗？
        String prePath = new ApplicationHome(this.getClass()).getDir().getParentFile().getParentFile().getAbsolutePath()
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "resources"
                + File.separator + "static"
                + File.separator + "images";

        String fullpath = prePath + File.separator + fileName;

        try {
            File dir = new File(prePath);
            if (!dir.exists()) dir.mkdirs();
            file.transferTo(new File(fullpath).getAbsoluteFile());
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.USER_ERROR_A0700);
        }

        String url = UrlBuilder.of()
                .setScheme("http")
                .setHost(NetUtil.getLocalhostStr())
                .setPort(port)
                .addPath("api")
                .addPath("images")
                .addPath(fileName)
                .build();

        return R.success(url);
    }
}
