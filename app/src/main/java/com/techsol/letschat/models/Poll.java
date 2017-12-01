package com.techsol.letschat.models;


import java.util.List;

public class Poll {
    Long id;
    String title;
    Long pollCreationTime;
    Long pollEndTime;
    PollType pollType;
    List<PollOption> optionList;

    enum PollType {
        TEXT,
        IMAGE
    }
}
