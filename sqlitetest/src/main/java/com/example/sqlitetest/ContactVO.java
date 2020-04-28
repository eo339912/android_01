package com.example.sqlitetest;

public class ContactVO {
    Integer _no;
    String name;
    String phone;
    Integer over20;

    public Integer get_no() {
        return _no;
    }

    public void set_no(Integer _no) {
        this._no = _no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOver20() {
        return over20;
    }

    public void setOver20(Integer over20) {
        this.over20 = over20;
    }


}
