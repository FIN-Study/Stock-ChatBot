package com.study.stockbot.wrapper;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class QuickReply {

  private String label;
  @Builder.Default
  private String action = "message";
  private String messageText;
  private String blockId;
  private Object extra;
}
