package com.droibit.github.rxconnpass


internal val mockResponse = """
{
    "events": [
        {
            "accepted": 45,
            "address": "東京都新宿区西新宿8-17-1　 住友不動産新宿グランドタワー 14Ｆ",
            "catch": "Tech-Circle #11 Chainer Handson",
            "description": "<h2>Chainerで自然言語処理を行い、日本語を英語に翻訳しよう！！</h2>
<p>Tech-Circle 機械学習編ではアプリケーションの中で機械学習を活用する方法をハンズオン形式で学んでいきます。<br>
今回は、ニューラルネットの実装が簡単なChainerを利用して機械翻訳にチャレンジしようと思います。</p>
<ul>
<li>簡単な機械翻訳について理解する</li>
<li>ニューラルネットについての概要を理解する</li>
<li>Chainerの開発環境を構築する</li>
</ul>
<p>「機械翻訳の役立たせ方」「ニューラルネットで機械翻訳を実装したい」、そんな不安や疑問を解消できればと思います。</p>
<p>※本ハンズオンの推奨OSはMacOS、Linuxです。</p>
<h2>開催概要</h2>
<ul>
<li>
<p>日時: 2015/12/10(木) 18:30-21:30 (18:30- 受付&amp;ネットワーキングタイム開始、19:30- 本編開始)</p>
</li>
<li>
<p>場所: TIS株式会社 東京本社 14F <a href="http://www.tis.co.jp/company/information/network/shinjuku.html" rel="nofollow">access</a></p>
</li>
<li>
<p>最寄駅: 東京メトロ丸ノ内線西新宿駅、JR新宿駅、他</p>
</li>
<li>
<p>定員: 38名 (スタッフ含む)</p>
<ul>
<li>※抽選方式を採っております。抽選日は会の10日前を予定しています。</li>
<li>※一般参加の方は、一般枠もしくはLT枠にてお申込みください。</li>
</ul>
</li>
</ul>
<h2>懇親会について</h2>
<p>希望者が多ければ、懇親会をします。</p>
<h2>想定参加者ターゲット</h2>
<ul>
<li>事前準備(後述)を勉強会当日までに実施し、事前準備したノートPCを当日持参できる</li>
<li>Pythonでプログラミングできる</li>
<li>機械学習・自然言語処理についての基本的な概念を理解している</li>
<li>ニューラルネットについての基本的な概念を理解している</li>
</ul>
<h2>タイムスケジュール</h2>
<table>
<thead>
<tr>
<th align="center">時間</th>
<th align="center">内容</th>
<th align="center">登壇者</th>
</tr>
</thead>
<tbody>
<tr>
<td align="center">18:30-19:30</td>
<td align="center">受付&amp;ネットワーキングタイム</td>
<td align="center"></td>
</tr>
<tr>
<td align="center">19:30-19:35</td>
<td align="center">Tech-Circleの概要説明</td>
<td align="center">@shiraco</td>
</tr>
<tr>
<td align="center">19:35-19:55</td>
<td align="center">Chainerを利用した機械翻訳</td>
<td align="center">@odashi_t</td>
</tr>
<tr>
<td align="center">19:55-20:05</td>
<td align="center">ハンズオン準備＆質問</td>
<td align="center"></td>
</tr>
<tr>
<td align="center">20:05-20:25</td>
<td align="center">Hands on #1 ニューラルネットのパラメータ調整</td>
<td align="center"></td>
</tr>
<tr>
<td align="center">20:25-20:45</td>
<td align="center">Hands on #2 データの学習かつ精度判定</td>
<td align="center"></td>
</tr>
<tr>
<td align="center">20:45-20:50</td>
<td align="center">質問タイム(ハンズオンのバッファ)</td>
<td align="center">参加者全員</td>
</tr>
<tr>
<td align="center">20:50-21:05</td>
<td align="center">LT　深層学習(chainer)でフレーズベース機械翻訳の並べ替えモデルを改善する</td>
<td align="center">@shin_kan0</td>
</tr>
<tr>
<td align="center">21:05-21:10</td>
<td align="center">アンケート &amp; 次回の案内</td>
<td align="center">全員</td>
</tr>
<tr>
<td align="center">21:10-21:20</td>
<td align="center">片付け</td>
<td align="center">スタッフ</td>
</tr>
</tbody>
</table>
<p>※タイムスケジュールは当日の進行状況により多少前後する可能性があります。ご了承下さい。</p>
<p><font color="red">※受付は19:30まです。それ以降の来場はハッシュタグ#techcirclejaにご連絡おねがいします。</font></p>
<h2>ハンズオン資料</h2>
<p>事前準備の資料以外はハンズオン後に公開します。</p>
<ul>
<li>
<p>事前準備 <a href="http://qiita.com/GushiSnow/items/c0dce54a1ed90fe16c26" rel="nofollow">Chainer ハンズオン ～事前準備編～ Tech-Circle#11</a></p>
</li>
<li>
<p><a href="http://www.slideshare.net/YusukeOda1/encoderdecoder-tis" rel="nofollow">Chainerを利用して機械翻訳にチャレンジしよう</a></p>
</li>
<li><a href="https://github.com/SnowMasaya/Chainer_Machine_Translation_ipython_notebook" rel="nofollow">Tech Circle ML #11 Chainerを利用して機械翻訳にチャレンジしよう</a></li>
</ul>
<hr>
<h2>内容詳細</h2>
<h2>事前準備(当日まで) <font color="red">◆事前準備のお願い◆</font></h2>
<p>今回は各自のPC上でハンズオンをしていただくことを想定しております。
当日のハンズオンをスムーズに進めるため、事前に以下の準備をお願いします。</p>
<p>下記作業手順は<a href="http://qiita.com/GushiSnow/items/c0dce54a1ed90fe16c26" rel="nofollow">Tech-Circle#11</a>をご覧下さい。</p>
<p>当日会場に共有の無線LAN環境はありますが、少しでも負荷を下げるために事前に上記対応お願いします。
事前に対応が難しい場合、当日の18:30-19:30に会場を事前開放いたします。
その時間で対応いただければと思います。その際にはスタッフメンバーもおりますのでお気軽に質問しながら進めていただければと思います。</p>
<p>また、当日NW環境を自分で準備可能な方につきましては、できる限り持参いただけると助かります。</p>
<h3>受付&amp;ネットワーキングタイム</h3>
<p>ネットワーキングタイムということで本編開始の1時間前に会場を開放します。
この時間に、参加者同士での交流(名刺交換やFacebookの友達申請など)やハンズオンの事前準備、コーヒーやお茶を飲んでちょっと休憩など有効に活用していただければと思っています。</p>
<p>飲み物の持ち込みは可能ですが、会場の都合上アルコールはご遠慮下さい。</p>
<h3>Chainerで自然言語処理を行い、日本語を英語に翻訳しよう！！</h3>
<p>機械翻訳の概要と、その特性について簡単に解説します。また、実装するに当たりどのような選択肢があるのかを提示します。
資料はハンズオン後に公開予定です。</p>
<p><a href="" rel="nofollow">Chainerで自然言語処理を行い、日本語を英語に翻訳しよう！！</a></p>
<h3>Handson</h3>
<p>ニューラルネットワークのライブラリChainerを使用し、機械翻訳を行うための基本的な手順を見ていきます。
資料はハンズオン後に公開予定です。</p>
<p><a href="http://qiita.com/GushiSnow/private/b0abf7d3dccafe14fa07" rel="nofollow">Tech Circle ML #11 Chainerで自然言語処理を行い、日本語を英語に翻訳しよう！！</a></p>
<h3>LT</h3>
<p>LT(15分枠)大会を予定しています。</p>
<ul>
<li><a href="https://twitter.com/shin_kan0" rel="nofollow">shin_kan0</a> 「LT　深層学習(chainer)でフレーズベース機械翻訳の並べ替えモデルを改善する」</li>
</ul>
<h3>アンケート</h3>
<p><a href="https://goo.gl/5Edbi4" rel="nofollow">アンケート</a></p>
<hr>
<h2>Tech-Circleとは？</h2>
<p><a href="http://techcircle.connpass.com" rel="nofollow">こちら</a>をご一読ください。
「技術に触れてきっかけづくりを」をコンセプトとして開催する勉強会です。
Tech-Circleでは、インフラエンジニア向けの技術とアプリケーション開発者向けの機械学習技術の大きく2つの軸をテーマとして勉強会を企画しています。</p>
<h3>過去のTech-Circleの勉強会</h3>
<ul>
<li><a href="http://techcircle.connpass.com/event/21960/" rel="nofollow">Tech-Circle #10: Tech-Circle Sparkでツイートをリアルタイム解析するWebアプリの開発(ハンズオン)</a></li>
<li><a href="http://techcircle.connpass.com/event/20042/" rel="nofollow">Tech-Circle #9: Tech-Circle Chainerを用いた自然言語処理でソースコード自動生成に挑戦(ハンズオン) </a></li>
<li><a href="http://techcircle.connpass.com/event/18906/" rel="nofollow">Tech-Circle #8: Chef Meetup (Tech-Circle&amp;CreationLine共催)</a></li>
</ul>
<p>全ての過去のイベントは<a href="http://techcircle.connpass.com/event/" rel="nofollow">こちら</a></p>
<h2>連絡先</h2>
<p>ご質問等はTwitterにて
<a href="https://twitter.com/ike_dai" rel="nofollow">@ike_dai</a>もしくは<a href="https://twitter.com/shiraco" rel="nofollow">@shiraco</a>までご連絡ください。</p>
<h2>ハッシュタグ</h2>
<p>このイベントのハッシュタグは<a href="https://twitter.com/hashtag/techcircleja?f=realtime" rel="nofollow"><strong>#techcircleja</strong></a>です！</p>",
            "ended_at": "2015-12-10T21:30:00+09:00",
            "event_id": 23365,
            "event_type": "participation",
            "event_url": "http://techcircle.connpass.com/event/23365/",
            "hash_tag": "techcircleja",
            "lat": "35.695876800000",
            "limit": 46,
            "lon": "139.690339800000",
            "owner_display_name": "shiraco",
            "owner_id": 2268,
            "owner_nickname": "shiraco",
            "place": "TIS株式会社 東京本社",
            "series": {
                "id": 954,
                "title": "Tech-Circle",
                "url": "http://techcircle.connpass.com/"
            },
            "started_at": "2015-12-10T18:30:00+09:00",
            "title": "Tech-Circle Chainerで自然言語処理を行い翻訳に挑戦(ハンズオン）",
            "updated_at": "2016-01-14T17:44:24+09:00",
            "waiting": 21
        },
        {
            "accepted": 0,
            "address": "〒150-0001 東京都渋谷区神宮前2丁目18-21",
            "catch": "シェアリングエコノミー関連のスタートアップを中心としたエンジニア向けのイベント（ミートアップ）です。",
            "description": "<h1>急成長しているサービスから学ぶ開発のコツ</h1>
<p>Sharing Economy Tech Meetupとは今勢いのあるシェアリングエコノミー関連のスタートアップを中心とした、エンジニア向けのミートアップ(イベント)です。
毎回イベントの後半に行う懇親会では軽食を交えながら参加者同士で情報交換していただける場をもうけますので、同じ興味を持った方々と繋がる機会にしていただければと思います。</p>
<p>今回は記念すべき第1回ということで豪華な方々に集まっていただきました！！
次の4社よりシェアリングエコノミーの可能性について、また各社の具体的なサービス開発についてお話しします。</p>
<p>・株式会社スペースマーケット（SPACEMARKET）</p>
<p>・株式会社レレレ（TimeTicket）</p>
<p>・株式会社SQUEEZE（Mister Suite）</p>
<p>・株式会社リクルートキャリア(IT戦略室)</p>
<h3>＜タイムスケジュール＞</h3>
<p>・日時：1/22 (金) 19:00-21:30</p>
<p>・場所：kurkku home
　　　　
〒150-0001 東京都渋谷区神宮前2-18-21　(<a href="http://www.kurkku-home.jp/" rel="nofollow">http://www.kurkku-home.jp/</a>)</p>
<table>
<thead>
<tr>
<th align="left">時間</th>
<th align="left">発表者</th>
<th>内容</th>
</tr>
</thead>
<tbody>
<tr>
<td align="left">18:45 〜 19:00</td>
<td align="left">-</td>
<td>受付</td>
</tr>
<tr>
<td align="left">19:00 〜 19:05</td>
<td align="left">舘林 真一(株式会社SQUEEZE CEO)</td>
<td>挨拶 &amp; 登壇者ご紹介</td>
</tr>
<tr>
<td align="left">19:05 〜 19:20</td>
<td align="left">重松 大輔(株式会社スペースマーケットCEO)</td>
<td>シェアリングエコノミーについて</td>
</tr>
<tr>
<td align="left">19:20 〜 19:40</td>
<td align="left">山本 大策(株式会社レレレ CEO)</td>
<td>失敗しにくいサービスの作り方</td>
</tr>
<tr>
<td align="left">19:40 〜 20:00</td>
<td align="left">関根 裕紀(株式会社SQUEEZE CTO)</td>
<td>Mister Suiteを支える技術</td>
</tr>
<tr>
<td align="left">20:00 〜 20:20</td>
<td align="left">田中 悠樹(株式会社リクルートキャリア IT戦略室 テクニカルマネージャー)</td>
<td>正解のないサービスの作り方</td>
</tr>
<tr>
<td align="left">20:30 〜 21:30</td>
<td align="left">-</td>
<td>懇親会</td>
</tr>
</tbody>
</table>
<h3>＜こんな方におすすめ！＞</h3>
<p>・拡大期のスタートアップに興味のある方</p>
<p>・シェアリングエコノミーに興味のある方</p>
<p>・サービス開発に興味のある方</p>
<p>・いま勢いのある業界の開発について知りたい方
　</p>
<h3>＜イベント概要＞</h3>
<p>最近良く耳にする民泊という言葉で有名なAirbnbなど余っているモノや時間、場所を今必要としている人に提供するシェアリングエコノミーという概念が欧米だけでなく日本にも広まってきています。この発展に伴い、シェアリングエコノミーに関する情報や技術を共有するネットワークを作ろう！ということで今回のイベント開催に至りました。トークの内容は、技術よりとなっておりますが、エンジニアでない方もご興味があれば奮ってご応募ください！</p>
<h3>＜登壇者紹介＞</h3>
<h4>・<a href="https://spacemarket.com/" rel="nofollow">「SPACEMARKET」</a></h4>
<h6>株式会社スペースマーケット 代表取締役 CEO 重松 大輔</h6>
<p>2000年NTT東日本入社。主に法人営業企画、プロモーション（PR誌編集長）等を担当。 2006年、当時10数名の株式会社フォトクリエイトに参画。 一貫して新規事業、広報、採用に従事。国内外企業とのアライアンス実績多数。 ゼロから立ち上げたウェディング事業は現在、全国で年間約3万組の結婚披露宴で 導入されるサービスまでに育つ。2013年7月東証マザーズ上場を経験。2014年1月、株式会社スペースマーケットを創業。</p>
<h4>・<a href="https://www.timeticket.jp/" rel="nofollow">「Time Ticket」</a></h4>
<h6>株式会社レレレ 代表取締役 CEO 山本 大策</h6>
<p>みずほ情報総研株式会社、フィードパス株式会社、株式会社リクルートメディアコミュニケーションズを経て、2012年2月にコーヒー1杯を飲む時間を一緒に過ごしたい人と出会えるサービス「CoffeeMeeting」を個人開発しリリース。2012年5月に株式会社レレレを設立。2014年7月、個人が気軽に空き時間を売買できるサービス「TimeTicket」をリリース。
「TimeTicket」ではリリース後3ヶ月で約5,000枚のチケットが発行され、売れた時間の合計も1,000時間を超えるなど、話題を呼んでいる。
　</p>
<h4>・<a href="https://www.mistersuite.com/" rel="nofollow">「Mister Suite」</a></h4>
<h6>株式会社SQUEEZE 代表取締役 CEO　舘林 真一</h6>
<p>東海大学政治経済学部卒業後、ゴールドマンサックス証券シンガポール支社に勤務。その後、トリップアドバイザー株式会社シンガポール支社にてディスプレイ広告の運用を担当。2014年9月、株式会社SQUEEZEを創業し代表取締役CEOに就任。</p>
<h6>株式会社SQUEEZE 取締役 CTO　関根 裕紀</h6>
<p>複数のスタートアップ、ベンチャー企業にて新規サービス開発やマネジメントを経験。2015年10月よりCTOに就任。コミュニティ活動として、PyCon JP 2015 副座長、「Pythonもくもく会」の主催。共著書に『Pythonエンジニア養成読本（2015 技術評論社刊）』がある。</p>
<h4>・<a href="https://www.recruitcareer.co.jp/" rel="nofollow">「リクルートキャリア」</a></h4>
<h6>株式会社リクルートキャリア  IT戦略室 テクニカルマネージャー 田中 悠樹</h6>
<p>新卒でゴールドマンサックス証券に入社。テクノロジー部にて、株式・債券の決済システムの構築に携わる。主に東南アジアやオーストラリアの決済システムの設計、実装や、日本銀行との新日銀決済システムの仕様詰めや実装に携わる。
その後、株式会社エニタイムズに転向し、同社にてCTOを経験。仕様設計やコーディング、インフラの管理から採用までITスタートアップの技術に関わる範囲を網羅する。
2015年8月より、株式会社リクルートキャリアに転籍。人材領域の新規事業立ち上げ部署において、エンジニアグループのテクニカルマネージャーとしてビジネス・エンジニアリング両面のマネージメントを行う。また、その中でもリクルートキャリアからリリースしているアプリ、”コシゴト”のプロダクトCTOを兼務。
2015年10月よりリクルートテクノロジーズに出向し、技術マネージメントだけでなく、エンジニア育成にも取り組む。</p>
<h2>その他</h2>
<p>質問等ございましたら hsekine@squeeze<a href="http://-inc.co.jp" rel="nofollow">-inc.co.jp</a> までお願いいたします。</p>",
            "ended_at": "2016-01-22T22:00:00+09:00",
            "event_id": 25214,
            "event_type": "participation",
            "event_url": "http://tokyosharingeconomy.connpass.com/event/25214/",
            "hash_tag": "",
            "lat": "35.672853900000",
            "limit": 30,
            "lon": "139.709821900000",
            "owner_display_name": "hsekine",
            "owner_id": 68167,
            "owner_nickname": "hsekine",
            "place": "kurkku home",
            "series": {
                "id": 1749,
                "title": "Tokyo Sharing Economy Startups",
                "url": "http://tokyosharingeconomy.connpass.com/"
            },
            "started_at": "2016-01-22T18:30:00+09:00",
            "title": "Sharing Economy Tech Meetup",
            "updated_at": "2016-01-14T15:47:31+09:00",
            "waiting": 0
        }
    ],
    "results_available": 683,
    "results_returned": 10,
    "results_start": 1
}
"""
