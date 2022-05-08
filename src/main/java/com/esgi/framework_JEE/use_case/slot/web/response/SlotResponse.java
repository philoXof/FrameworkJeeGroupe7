package com.esgi.framework_JEE.use_case.slot.web.response;

import java.util.Date;

public class SlotResponse {
    public int id;
    public String start;
    public String end;

    public SlotResponse() {
    }

    public int getId() {
        return id;
    }

    public SlotResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getStart() {
        return start;
    }

    public SlotResponse setStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public SlotResponse setEnd(String end) {
        this.end = end;
        return this;
    }
}
