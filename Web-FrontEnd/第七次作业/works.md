## LV I

### 背景

> 中文房间（英语：Chinese room），是由美国哲学教授约翰·希尔勒提出的一个思想实验，借以反驳强人工智能的观点。根据强人工智能的观点，只要计算机拥有适当的程序，理论上就可以说计算机拥有它的认知状态并且可以像人一样进行理解活动。
>
> 该实验出自约翰·罗杰斯·希尔勒的论文《心灵、大脑和程序》（*Minds, Brains, and Programs*）中，发表于1980年的《行为与脑科学》。
>
> 中文房间的实验过程可表述如下：
>
> 一个对中文一窍不通，只说英语的人关在一间只有一个开口的封闭房间中。房间里有一本用英文写成的手册，指示该如何处理收到的汉语讯息及如何以汉语相应地回复。房外的人不断向房间内递进用中文写成的问题。房内的人便按照手册的说明，查找合适的指示，将相应的中文字符组合成对问题的解答，并将答案递出房间。
>
> 约翰·希尔勒认为，尽管房里的人可以以假乱真，让房外的人以为他说中文，但事实上他压根儿不懂中文。在上述过程中，房外人的角色相当于程序员，房中人相当于计算机，而手册则相当于计算机程序：每当房外人给出一个输入，房内的人便依照手册给出一个答复（输出）。而正如房中人不可能通过手册理解中文一样，计算机也不可能通过程序来获得理解力。既然计算机没有理解能力，所谓“计算机于是便有智能”便更无从谈起了。

钰姐对 fufu 进行了系统升级，为了验证其现在能否通过图灵测试，决定使用“中文室”对其检测。

### 题目

现给定一接口 `/homework/fufubot_test/`，可通过它使用 `POST` 方法向服务器发送任意文本消息，其返回值亦为文本。

- 要求：用户可在前端的输入框输入文本内容，点击发送按钮后发送消息，并将响应信息显示在界面上。

### NEXT LV

fufu 的每次回复均有五分之一的概率出现下一道题的题目。

## LV II 
### 题目 现给定两个接口： 
#### `POST /homework/auth/login` 
- Content-Type: `application/json` 
- Parameters: 
    - `username: string`: 用户名 
    - `password: string`: 密码 
- Response: text 
使用此接口用以登录，用户名使用 `admin`，密码使用 `root`。其返回值为 token。 
#### `GET /homework/auth/next_level` 
- Response: text 
使用此接口用以获取下一等级题目，请求时其请求头中的 `Authorization` 需设为 `BEARER ${token}`，例：`BEARER eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZnVmdSJ9`. 
- 要求：使用题目给定接口获取到下一题的题目，并实现保存登录状态，即重启浏览器后无需再次登录即可正常调用 `/auth/next_level` 接口。 
- 延伸阅读：[JSON Web Token 入门教程](http://www.ruanyifeng.com/blog/2018/07/json_web_token-tutorial.html) 

## LV III

### 题目

现给定一接口 `ws://anonym.ink:8000/homework/chatroom?username=xxx`，可通过它与服务器建立 WebSocket 连接。其中参数 `username` 是你的进入聊天室时的用户名，为空则默认为 `Anonymous`. 

当有人进入聊天室时，会收到以下格式内容：

```javascript
{
    type: "OPEN",
    username: "李小钰",
    avatar: "https://avatars.githubusercontent.com/u/87082575?v=4"
}
```

当有人发送信息时，会收到以下格式内容：

```javascript
{
    type: "MESSAGE",
    username: "马小仪",
    data: "前端的技术栈哪个我没学过？布鲁奈尔的钰姐，不知道比你们高到哪里去了，我和她谈笑风生。",
    avatar: "https://avatars.githubusercontent.com/u/72426675?v=4"
}
```

当有人退出聊天室时，会收到以下格式内容：

```javascript
{
    type: "CLOSE",
    username: "曾小捷",
    avatar: "https://avatars.githubusercontent.com/u/72637162?v=4"
}
```


https://avatars.githubusercontent.com/u/69996135?v=4


- 要求：使用题目给定接口完成一个优(jiǎn)雅(lòu)的聊天室，并提交证明可以在不同的设备之间互相吹水的截图（比如试着和你的小伙伴们在里面一起吹水）；
- 扩展项：实现图片的发送与接收功能（使用绝对链接实现即可）。

### NEXT LV

无，但服务器每隔五分钟会向与服务器建立连接的客户端广播一次彩蛋。
