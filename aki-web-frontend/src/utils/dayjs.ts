import dayjs from 'dayjs';
import relativeTime from 'dayjs/plugin/relativeTime'
import minMax from 'dayjs/plugin/minMax'
import cn from 'dayjs/locale/zh-cn'

dayjs.locale(cn);
dayjs.extend(relativeTime);
dayjs.extend(minMax);


export default dayjs;