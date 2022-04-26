package com.esgi.framework_JEE.use_case.slot.web.response;

import java.util.Date;

public class SlotResponse {
    public int id;
    public Date start;
    public Date end;

    public SlotResponse() {
    }

    public int getId() {
        return id;
    }

    public SlotResponse setId(int id) {
        this.id = id;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public SlotResponse setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public SlotResponse setEnd(Date end) {
        this.end = end;
        return this;
    }
}
