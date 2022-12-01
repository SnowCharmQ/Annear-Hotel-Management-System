<template>
  <div id="app">
    <vam-video
      :properties="videoOption.properties"
      :videoStyle="videoOption.videoStyle"
      :controlsConfig="videoOption.controlsConfig"
      @play="playVideo"
      @canplay="canplayVideo"
      @pause="pauseVideo"
    ></vam-video>
  </div>
</template>

<script>
import VamVideo from "vue-vam-video";
export default {
  name: "App",
  components: {
    VamVideo
  },
  data: () => ({
    videoOption: {
      properties: {
        poster: require("../../assets/images/home1.png"),
        src:
          "https://mos-vod-drcn.dbankcdn.cn/P_VT/video_injection/2A1343EFA/v3/6CC21C811065945606293295744/MP4Mix_H.264_1920x1080_6000_HEAAC1_PVC_NoCut.mp4",
          
        preload: "auto",
        // loop: "loop",
        // autoplay:"autoplay",
        // muted:true
      },
      videoStyle: {
        // width: "1200px",
        // height: "600px",
      },
      controlsConfig: {
        fullScreenTit:"全屏",
        EscfullScreenTit:"退出全屏",
        speedTit:"倍速",
        yinliangTit:"音量",
        jingyinTit:"静音",
        playTit:"播放",
        pauseTit:"暂停",
        fullScreen:true,
        speed:true,
        listen:true
      }
    },
  }),
  methods:{
    playVideo(){
      console.log("play");
    },
    pauseVideo(){
      console.log("pause");
    },
    canplayVideo(){
      console.log("canplay");
    }
  }
};
</script>