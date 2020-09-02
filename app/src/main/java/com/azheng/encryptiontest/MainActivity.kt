package com.azheng.encryptiontest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

//
class MainActivity : AppCompatActivity() {

    private var content =
        "{\"title\":\"json在线解析（简版） -JSON在线解析\",\"json.url\":\"https://www.sojson.com/simple_json.html\",\"keywords\":\"json在线解析\",\"功能\":[\"JSON美化\",\"JSON数据类型显示\",\"JSON数组显示角标\",\"高亮显示\",\"错误提示\",{\"备注\":[\"www.sojson.com\",\"json.la\"]}],\"加入我们\":{\"qq群\":\"259217951\"}}"
    private var decryptSrc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnJiami.setOnClickListener {
            var src = AESUtils.aESEncrypt(content)
            decryptSrc = src
            tvJiami.text = src
        }

        btnJiemi.setOnClickListener {
            var src2 = AESUtils.aESDecrypt(decryptSrc)
            tvJiemi.text = src2
        }

    }


}