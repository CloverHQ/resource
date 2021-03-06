package com.aaron.resource.website.pojo;

import lombok.Data;

@Data
public class TbWxAccount {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.province
     *
     * @mbggenerated
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.city
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.avatar_url
     *
     * @mbggenerated
     */
    private String avatarUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.gender
     *
     * @mbggenerated
     */
    private Byte gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.nick_name
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.country
     *
     * @mbggenerated
     */
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.language
     *
     * @mbggenerated
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.openid
     *
     * @mbggenerated
     */
    private String openid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_wx_account.session_key
     *
     * @mbggenerated
     */
    private String sessionKey;
}