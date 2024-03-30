/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { R } from '../models/R';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class TestControllerService {
    /**
     * @returns R OK
     * @throws ApiError
     */
    public static list(): CancelablePromise<R> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/test/list',
        });
    }
    /**
     * @returns R OK
     * @throws ApiError
     */
    public static hello(): CancelablePromise<R> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/test/hello',
        });
    }
}
