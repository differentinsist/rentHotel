package com.renthotel.auth.test;

import com.renthotel.common.pojo.UserInfo;
import com.renthotel.common.utils.JwtUtils;
import com.renthotel.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    //    本机的公钥和私钥路径（记得改）
    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";
    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test  // 你在运行这个test里面的方法时；要把下面Before的方法注释掉；不然无法读取秘钥  把@Before注解注释就行；然后运行后面的放开
    public void testRsa() throws Exception {   // 盐
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "1234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(1, "admin"), privateKey);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiJ9.kL5hFIrGZ8ctjyrsFN-AyoSdaPdP2ugW4e5neyZO6NcnBz7uvAUtkx1tDunITAIQ3mxLNT184otKlf6FlokY1e5V0nOojkdYDrWyr4GHLP_vPpLzDH_81elVwJouSDLmL18qKT_yYj25W5ayvupE8cwYohAAHV0gYORIt5X2W7U";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
