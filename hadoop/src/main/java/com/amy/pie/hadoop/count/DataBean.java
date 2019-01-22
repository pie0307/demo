package com.amy.pie.hadoop.count;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2018/5/11 15:33
 */
public class DataBean extends Text implements Writable {
    private String url;
    private String ips;

    public DataBean() {
    }

    public DataBean(String url, String ips) {
        this.url = url;
        this.ips = ips;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(url);
        out.writeUTF(ips);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.url = in.readUTF();
        this.ips = in.readUTF();
    }
}
