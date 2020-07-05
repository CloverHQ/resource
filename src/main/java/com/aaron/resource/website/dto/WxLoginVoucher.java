package com.aaron.resource.website.dto;

import lombok.Data;

@Data
public class WxLoginVoucher {
    private String openid;
    private String session_key;
}
