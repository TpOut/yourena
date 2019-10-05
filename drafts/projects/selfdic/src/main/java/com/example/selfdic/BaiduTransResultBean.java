package com.example.selfdic;

import java.util.List;

public class BaiduTransResultBean {
    private String from;

    private String to;

    private List<Trans_result> trans_result;

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return this.from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }

    public void setTrans_result(List<Trans_result> trans_result) {
        this.trans_result = trans_result;
    }

    public List<Trans_result> getTrans_result() {
        return this.trans_result;
    }

    public class Trans_result {
        private String src;

        private String dst;

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrc() {
            return this.src;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        public String getDst() {
            return this.dst;
        }
    }
}
