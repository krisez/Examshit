# MRedRock's 考核 QQMusic 热榜
## 加载进入app。首先用了一个等待界面（其实想通过sp进行用户判断先看哪一个视角，但是CheckBox蛋疼了一把）
## 然后进入主界面在这里分为四个Fragment进行ViewPager，下拉进行刷新重新加载adapter，没有再加载更多（定值加载歌曲数量（哭~））
<img src="https://github.com/krisez/Examshit/blob/master/gif/first.gif" width="200px" height="250px"/>
<br/>
## 点击一个item获取起绑定了的专辑图片，流媒体地址等等传入播放的Activity
<img src="https://github.com/krisez/Examshit/blob/master/gif/play.gif" width="200px" height="250px"/>
<br/>
## 点击searchButton得到搜索的Activity，然后通过EditText得到keyword返回相应的内容
<img src="https://github.com/krisez/Examshit/blob/master/gif/search.gif" width="200px" height="250px"/>
<br/>
## 播放过的历史列表，因为不知道数据库怎么去操作Bitmap（将图片下载下来(发呆)），所以蛋疼了一把。最后还是决定要歌曲名，歌手和url
<img src="https://github.com/krisez/Examshit/blob/master/gif/list.gif" width="200px" height="250px"/>
<br/>
## 最后的歌曲下载通过长按item实现(本来想玩QQ那种长按蹦出来小的选项框的，但是崩了几次了)
<img src="https://github.com/krisez/Examshit/blob/master/gif/end.gif" width="200px" height="250px"/>
<br/>
##### 小小字体说一句，解析用的Gson,会死么（忐忑~~~）
