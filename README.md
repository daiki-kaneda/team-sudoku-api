String teamSudokuReadme = """
# 🧩 Team Sudoku API

リアルタイムで複数人が同時にプレイできる、協力型数独アプリケーションのバックエンドAPIです。
データの整合性とバリデーションをこのAPIが担当し、リアルタイム配信は **Supabase Realtime** を活用する構成を採用しています。

## 🚀 主な機能

* **協力プレイ**
    * 複数ユーザーによる同時編集
    * 誤答時のペナルティ共有やクリア判定
* **リアルタイム同期 (Supabase Realtime)**
    * DBへの変更をトリガーに、他プレイヤーへ即座に盤面更新を通知
* **リプレイ・履歴管理**
    * イベントソーシング的なアプローチによる、操作ログの記録と再生
    * 盤面の状態（State）を持たず、ログから現在地を計算する堅牢な設計

## 🛠 技術スタック

* **Language:** Java 17
* **Framework:** Spring Boot 3.5
* **Database:** PostgreSQL (Supabase)
* **Real-time:** Supabase Realtime (Change Data Capture)
* **ORM:** Spring Data JPA (Hibernate)
* **Migration:** Flyway
* **Security:** Firebase Authentication

## 💡 こだわった設計ポイント

### 1. 責務の分離とリアルタイム配信
バックエンド（Spring Boot）はWebSocketを持たず、純粋なREST APIとして「ルールの判定（数字が合っているか）」と「データの保存」に専念させています。
クライアントへの変更通知には **Supabase Realtime** を採用することで、アプリケーションサーバーの負荷を下げつつ、信頼性の高いリアルタイム同期を実現しました。

### 2. イベント駆動と整合性 (Log-based Architecture)
盤面の「現在の状態」をDBに保存するのではなく、**「誰が・どこに・何を入れたか」という操作ログ（Log）** のみを保存する設計を採用しました。
* **競合の解消:** ログを積み上げる方式のため、同時編集時の書き込み衝突が発生しにくい設計です。
* **N+1問題の回避:** 盤面構築時にログを一括取得（Batch Fetch）し、メモリ上で再構築することでDBアクセスを最小限に抑えています。

### 3. ポータビリティとセキュリティ
`The Twelve-Factor App` の原則に基づき、機密情報（DB接続情報やFirebase Admin SDKなど）をコードから排除し、全て環境変数で注入する設計にしています。
