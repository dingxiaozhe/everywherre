package com.example.administrator.everywherretrip.bean;

public class OutData {
    /**
     * code : 0
     * desc :
     * results : {}
     */

    private int code;
    private String desc;
    private ResultsBean results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean {
    }
}
