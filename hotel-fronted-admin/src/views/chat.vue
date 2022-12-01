<template>
  <div style="padding: 10px; margin-bottom: 50px">
    <el-row>
      <el-col :span="4">
        <el-card style="width: 300px; height: 400px; margin-left: auto; color: #333">
          <div style="padding-bottom: 10px; border-bottom: 1px solid #ccc">Chatring Users<span style="font-size: 12px">（点击聊天气泡开始聊天）</span>
          </div>
          <div style="padding: 10px 0" v-for="user in users" :key="user.username">
            <span>{{ user.username }}</span>
            <i class="el-icon-chat-dot-round" style="margin-left: 10px; font-size: 16px; cursor: pointer"
               @click="initDialog(user.username)"></i>
            <span style="font-size: 12px;color: limegreen; margin-left: 5px" v-if="user.username === chatUser">chatting...</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="20">
        <div style="width: 650px; margin: 0 auto; background-color: white;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc">
          <div style="text-align: center; line-height: 50px;">
            Annear Chatting（{{ chatUser }}）
          </div>
          <div style="height: 400px; width: 650px; overflow:auto; border-top: 1px solid #ccc" v-html="content"></div>
          <div style="height: 200px">
            <textarea v-model="text" style="height: 160px; width: 610px; padding: 20px; border: none; border-top: 1px solid #ccc;
             border-bottom: 1px solid #ccc; outline: none"></textarea>
            <div style="text-align: right; padding-right: 10px">
              <el-button type="primary" size="mini" @click="send">Send</el-button>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
let socket;
let baseUrl = 'http://localhost:12345';
export default {
  name: "chat",
  data() {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      user: {},
      isCollapse: false,
      users: [],
      chatUser: '',
      text: "",
      messages: [],
      content: ''
    }
  },
  created() {
    this.init()
  },
  methods: {
    send() {
      if (!this.chatUser) {
        this.$message({type: 'warning', message: "Please choose a chatting target"})
        return;
      }
      if (!this.text) {
        this.$message({type: 'warning', message: "Please write your content"})
      } else {
        if (typeof (WebSocket) == "undefined") {
          console.log("Not support WebSocket");
        } else {
          // {"from": "zhang", "to": "admin", "text": "聊天文本"}
          let message = {from: this.user, to: this.chatUser, text: this.text}
          socket.send(JSON.stringify(message));
          this.messages.push({user: this.user, text: this.text})
          // 构建消息内容，本人消息
          this.createContent(null, this.user, this.text)
          this.text = '';
        }
      }
    },
    createContent(remoteUser, nowUser, text) {  // 这个方法是用来将 json的聊天消息数据转换成 html的。
      let html
      // 当前用户消息
      if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
            "    <div class=\"tip left\">" + text + "</div>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-2\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "</div>";
      } else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
        html = "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
            "  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
            "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
            "    <img src=\"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png\" style=\"object-fit: cover;\">\n" +
            "  </span>\n" +
            "  </div>\n" +
            "  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
            "    <div class=\"tip right\">" + text + "</div>\n" +
            "  </div>\n" +
            "</div>";
      }
      // console.log(html)
      this.content += html;
    },
    initDialog(username) {
      if (this.chatUser != username) {
        this.content = ''
        this.chatUser = username
        let message = {text: 'loadDialog'}
        socket.send(JSON.stringify(message));
        console.log(this.chatUser)
      }
    },
    init() {
      let _this = this;
      let params = {'token': sessionStorage.getItem('token')};
      this.$get(baseUrl + '/auth/getUsernameByToken', params).then(data => {
        let resp = data.data;
        console.log('hi')
        _this.user = resp

        console.log(this.user)
        let username = this.user
        if (typeof (WebSocket) == "undefined") {
          console.log("您的浏览器不支持WebSocket");
        } else {
          console.log("您的浏览器支持WebSocket");
          let socketUrl = "ws://localhost:11000/imserver/" + username;
          if (socket != null) {
            socket.close();
            socket = null;
          }
          // 开启一个websocket服务
          socket = new WebSocket(socketUrl);
          //打开事件
          socket.onopen = function () {
            console.log("websocket已打开");
          };
          //  浏览器端收消息，获得从服务端发送过来的文本消息
          socket.onmessage = function (msg) {
            console.log("收到数据====" + msg.data)
            let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
            if (data.users) {  // 获取在线人员信息
              _this.users = data.users.filter(user => user.username !== username)  // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
            } else if (data.load) {
              for (var i in data.load) {
                console.log(data.load[i])
                let text = data.load[i].text
                let from = data.load[i].from
                let to = data.load[i].to
                if (from == username) {
                  _this.messages.push(text)
                  _this.createContent(null, username, text)
                } else if (from == _this.chatUser) {
                  _this.messages.push(text)
                  _this.createContent(_this.chatUser, null, text)
                }
              }
            } else {
              if (data.from === _this.chatUser) {
                _this.messages.push(data)
                _this.createContent(data.from, null, data.text)
              }
            }
          };
          //关闭事件
          socket.onclose = function () {
            console.log("websocket closed");
          };
          //发生了错误事件
          socket.onerror = function () {
            console.log("websocket error");
          }
        }

      })
    }
  }
}
</script>
<style>
.tip {
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
  padding: 10px;
  width: auto;
  display: inline-block !important;
  display: inline;
}

.right {
  background-color: rgb(35, 182, 187);
}

.left {
  background-color: rgb(29, 185, 89);
}
</style>
