package main

import (
	"fmt"
	"time"
)

type Video struct {
	// 标题
	Title string
	// 播放量
	Count int
	// 弹幕数
	BarrageCount int
	// 日期时间
	Date string
	// 正在观看
	Watching int
}

type Author struct {
	// 名字
	Name string
	// 头像
	Icon string
	// 关注数
	Focus int
	// 签名
	Signature string
	// 带会员
	Vip bool
}

type Barrage struct {
	// 弹幕时间
	Time string
	// 内容
	Content string
	// 发送时间
	Date string
}

type Recommend struct {
	Icon string
	Title string
	Count int
	BarrageCount int
}

type Under struct {
	ThumbsUp int
	Coin int
	Collect int
	Transmit int
}

type BiliBili struct {
	Video Video
	Author Author
	BarrageList []Barrage
	Recommend []Recommend
	Under Under
}

func main() {
	video := BiliBili {
		Video: Video{
			Title: "「悲しみは水のよう」-原创rap付（《我的悲伤是水做的》日文版）填词翻唱【夏野】",
			Count: 258000,
			BarrageCount: 320,
			Date: "2020-04-26 10:00:31",
			Watching: 1,
		},
		Author: Author{
			Name: "夏野Nana",
			Icon: "巴拉巴拉",
			Focus: 11000,
			Signature: "",
			Vip: true,
		},
		BarrageList: []Barrage{
			{
				Time: "01:10",
				Content: "没字幕有点难受",
				Date: "4-27 06:36",
			},
			{
				Time: "00:53",
				Content: "好听",
				Date: "4-27 12:15",
			},
		},
		Recommend: []Recommend{
			{
				Icon: "巴拉巴拉",
				Title: "【一战爆发107周年】我的悲伤是血做的",
				Count: 347000,
				BarrageCount: 506,
			},
		},
		Under: Under{
			Coin: 11000,
			ThumbsUp: 21000,
			Collect: 17000,
			Transmit: 1498,
		},
	}
	fmt.Println(video)
	video.All()
	fmt.Println(video)
	fmt.Println(publish("寒雨", "测试视频"))
}

// Coin 投币
func (b *BiliBili) Coin() {
	b.Under.Coin++
}

// ThumpsUp 点赞
func (b *BiliBili) ThumpsUp() {
	b.Under.ThumbsUp++
}

// Collect 收藏
func (b *BiliBili) Collect() {
	b.Under.Collect++
}

// All 三连
func (b *BiliBili) All() {
	b.Collect()
	b.Coin()
	b.ThumpsUp()
}

func publish(author string, title string) BiliBili {
	return BiliBili{
		Author: Author{
			Name: author,
			Icon: "巴拉巴拉",
			Focus: 0,
			Signature: "巴拉巴拉",
			Vip: false,
		},
		Video: Video{
			Title: title,
			Count: 0,
			BarrageCount: 0,
			Date: time.Now().String(),
			Watching: 0,
		},
	}
}
