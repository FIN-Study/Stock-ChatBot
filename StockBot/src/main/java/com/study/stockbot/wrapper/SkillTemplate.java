package com.study.stockbot.wrapper;

import com.study.stockbot.wrapper.skill.Skills;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
// https://i.kakao.com/docs/skill-response-format#skilltemplate
public class SkillTemplate {

    final static private Character MAX_OUTPUTS_SIZE = 3;
    final static private Character MIN_OUTPUTS_SIZE = 1;
    final static private Character MAX_QUICKREPLY_SIZE = 10;

    @Singular("addOutput")
    private List<Skills> outputs;

    @Singular("addQuickReply")
    private List<QuickReply> quickReplies;

    public SkillTemplate(){
        this.outputs = new ArrayList<>();
        this.quickReplies = new ArrayList<>();
    }

    public SkillTemplate(List<Skills> ou, List<QuickReply> qr){
        this.outputs = ou;
        this.quickReplies = qr;
    }

    public void addOutput(Skills ou){
        if(this.outputs.size() + 1 > MAX_OUTPUTS_SIZE){
            throw new IndexOutOfBoundsException("outputs size must be at least 1 and no more than 3.");
        }

        this.outputs.add(ou);
    }

    public void initOutputs(){

        this.outputs = new ArrayList<Skills>();
    }

    public void setOutputs(ArrayList<Skills> outputs){

        if(outputs.size() > MAX_OUTPUTS_SIZE){
            throw new IndexOutOfBoundsException("outputs size must be at least 1 and no more than 3.");
        }
        this.outputs = outputs;
    }

    public void setOutput(Skills outputs){

        this.outputs = new ArrayList<Skills>();
        this.outputs.add(outputs);
    }

    public void addQuickReply(QuickReply qr){

        if(this.quickReplies.size() + 1 > MAX_QUICKREPLY_SIZE){
            throw new IndexOutOfBoundsException("quickReplies size must be no more than 10.");
        }
        this.quickReplies.add(qr);
    }

    public void initQuickReplies(){
        this.quickReplies = new ArrayList<QuickReply>();
    }

    public void setQuickReplies(ArrayList<QuickReply> qreply){

        if(qreply.size() > MAX_QUICKREPLY_SIZE){
            throw new IndexOutOfBoundsException("quickReplies size must be no more than 10.");
        }
        this.quickReplies = qreply;
    }
}
