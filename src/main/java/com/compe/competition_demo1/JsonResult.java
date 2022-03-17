package com.compe.competition_demo1;

public class JsonResult<T> {

    private T data;
    private String code;
    //private String msg;

    /**
     * 若没有数据返回，默认状态码为 700，提示信息为“无结果”
     */
    public JsonResult() {
        this.code = "700";
    //    this.msg = "无结果";
    }

    public JsonResult(int data){
        if(data==0)
            this.code="700";   //返回0代表操作失败
        else
            this.code="666";   //返回1代表操作成功
    }
    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
    //    this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 666，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = "666";
        //this.msg = "查询成功！";
    }

    /**
     * 有数据返回，状态码为 666，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = "666";
    //    this.msg = msg;
    }
    // 省略 get 和 set 方法
}
