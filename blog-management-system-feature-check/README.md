※このファイルは markdown 形式で記載しているためプレビューで開くと見やすいです。

# 完成図

これからブログ管理システムを作成していきます。  
以下 URL が完成版なので触ってみてください。  
※ 無料サーバーで公開しているため、開くのに時間がかかります。

https://blog-management-system-o636.onrender.com/

## 前提

- 課題はこのプロジェクトを上書きする形で進めてください。
  新規でプロジェクトを作成する必要はありません。

- Freeks において`spring-boot-starter-data-jpa`のライブラリを使用して、データベースとやりとりをしていましたが
  JDBC の理解を深めてもらうために、この課題では使用しませんので、ご認識お願い致します。

# 環境構築

## データベース作成

pgAdmin4 で `blog-system` というデータベースを作成してください。  
ユーザー名とパスワードは`postgres`という名称で作成してください。

## テーブル作成

ブログシステムを作成するにあたって、まずブログに関するデータを管理します。
データベース`blog-system` に `Blog` テーブルを`resources/db/create_blogs_table.sql`に下記のカラムを用意し作成してください。

- ID
- タイトル
- 内容
- 作成日時
- 更新日時
- 削除日時

また、作成日時と更新日時はデフォルトで現在時刻をセットするようにしてください。

### 挑戦

NOT NULL 制約で空文字を保存できないようにしてみよう！

削除日時がある理由を調べてみよう！

## パッケージ作成

`blog-management-system/src/main/java/com/example/blog`配下に下記のパッケージ構成になるように作成してください。

```
└── blog
    ├── BlogApplication.java
    ├── controller
    ├── entity
    ├── form
    ├── repository
    ├── service
    └── util
```

### 各パッケージの説明

`controller`: HTTP リクエストを受け取り、ユーザーからの要求を処理するためのロジックを持ちます。Web アプリケーションにおける「入口」の役割を果たします。

`entity`: データベースのテーブルに対応する Java オブジェクトを定義するクラスを定義します。データの保存形式を表現する「モデル」部分です。

`form`: フロントエンド（HTML フォームなど）から送信されるデータを受け取るためのデータオブジェクトを定義します。

`repository`: データベースとのやりとりを担当します。エンティティを使って、データベースからのデータ取得や保存、更新、削除などを行います。

`service`: ビジネスロジックを含むクラスを配置します。コントローラとリポジトリの間に位置し、データ処理や業務上の処理を実装します。

`util`: 共通して使われる便利機能を管理します。再利用性を高めるために使用します。

# ブログ一覧取得機能

ここから本格的にブログシステムを作成していきます。
基本的な Web アプリケーションの処理として、データの作成、読み取り、更新、削除の 4 つがあります。
まず読み取りの部分から実装していきましょう。

以下の手順でブログ記事の一覧取得機能を作成していきましょう。

## Entity

Entity の役割を再掲しますが、データベースのテーブルに対応する Java オブジェクトを定義するクラスを定義します。

`Blog` テーブルのカラムに合うように Entity パッケージに`Blog.java`を作成してください。
併せて Getter,Setter を用意してください。

## Repository(Mapper Interface)

データベースへの CRUD 処理を定義する Mapper Interface を作成してください。

repository パッケージに BlogMapper.java を作成してください。
Spring Boot から認識されるように @Mapper アノテーションを付与してください。
（もしくは、アプリケーションの設定で @MapperScan を使用しても構いません）

BlogMapper に以下のメソッドを定義してください。

・一覧取得
  List<Blog> findAll();
  削除日時が null のブログ記事を取得し、ID が降順になるようにしてください。

・ID 指定で取得
  Blog findById(int id);
  指定した ID のブログ記事を取得し、削除日時が null のものだけ返してください。

・新規登録
  void save(Blog blog);
  Blog オブジェクトのタイトルと内容をデータベースに保存し、作成日時・更新日時は現在時刻をセットしてください。

・更新
  void update(Blog blog);
  Blog オブジェクトのタイトルと内容を更新し、更新日時は現在時刻に設定してください。

・削除
  void delete(int id);
  指定した ID のブログ記事の削除日時に現在時刻をセットしてください。 


## Mapper XML の実装

Mapper Interface で定義した SQL を実際に記述する XML ファイルを作成してください。

resources/mapper フォルダに BlogMapper.xml を作成してください。
MyBatis 3 の標準形式で作成し、必ず DOCTYPE を記述してください。

mapper タグの namespace 属性には、対応する Interface（com.example.blog.repository.BlogMapper）の完全修飾名を指定してください。

Interface で定義したメソッドごとに SQL を記述してください。
メソッド名と XML 内の id は必ず一致させてください。

・一覧取得 (findAll)
  削除日時が null のデータを取得し、ID が降順になる SELECT 文を作成してください。

・ID 指定で取得 (findById)
  指定された ID かつ削除日時が null のブログ記事を取得する SELECT 文を作成してください。

・新規登録 (save)
  タイトルと内容を INSERT し、作成日時・更新日時は CURRENT_TIMESTAMP を設定してください。
  自動採番された ID は keyProperty="id" で取得できるようにしてください。

・更新 (update)
  タイトルと内容を UPDATE し、更新日時は CURRENT_TIMESTAMP に設定してください。
  削除日時が null のものだけ更新対象にしてください。

・削除 (delete)
  指定した ID の削除日時を CURRENT_TIMESTAMP に設定する UPDATE 文を作成してください。


## Service

再掲しますが、Service パッケージはビジネスロジックを含むクラスを配置します。コントローラとリポジトリの間に位置し、データ処理や業務上の処理を実装します。

service パッケージに`BlogService.java`を作成してください。

`list`メソッドを作成し、Repository の`findAll`メソッドを呼び出して、全ての Blog オブジェクトを返却してください。

### 挑戦

@Autowired アノテーションを使用して、実装してみよう

## Controller

Service の`list`メソッドを呼び出して、全ての Blog オブジェクトを View に渡してください。

`src/main/resources/templates/blog/list.html`を表示してください

## 動作確認

期待通りに登録されたデータが全取得できているか確認しましょう。

以下のクエリを実行してください。

```
INSERT INTO blogs (title, content, deleted_at)
VALUES
('First Blog', 'Content of the first blog', NULL),
('Second Blog', 'Content of the second blog', NULL),
('Third Blog', 'Content of the third blog', CURRENT_TIMESTAMP + INTERVAL '1 hour');
```

サーバーを起動して、`localhost:8080/blogs`にアクセスして、2 件取得できることを確認できたら正常に実装ができています！

# ブログ記事作成機能

以下の手順でブログ記事の作成機能を作成していきましょう。

## Form

Form クラスの役割を再掲しますが、フロントエンド（HTML フォームなど）から送信されるデータを受け取るためのデータオブジェクトを定義します。

form パッケージに`BlogForm.java`を作成してください。  
どのフィールドを定義するか考えてみてください。

## Repository（Mapper Interface）

データベースに保存するための Mapper Interface を作成します。

save メソッドを定義してください。
・引数：Blog オブジェクト
・戻り値：なし（void）
・処理内容：タイトルと内容を INSERT し、作成日時・更新日時は CURRENT_TIMESTAMP を設定

## Mapper XML

Mapper Interface に対応する SQL を resources/mapper/BlogMapper.xml に定義します。
・MyBatis 3 の標準形式で作成（DOCTYPE を含む）
・save メソッドと同じ id を使う
・SQL では INSERT INTO blogs (title, content, created_at, updated_at) VALUES (#{title}, #{content}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) の形で保存

## Service

ビジネスロジックを作成してください。

`create`メソッドを作成し、Form から Entity に詰め直して保存するように実装してください

## Controller（Get リクエスト）

ブログ作成画面のリクエストが来た際にフォームをレスポンスする実装をしてください。

以下要件です。

- `create`メソッドを作成してください。
- `/blogs/new`にリクエストが来た際に動作するようにしてください。
- `src/main/resources/templates/blog/form.html`を表示してください

## Controller（Post リクエスト）

ブログ作成のリクエストが来た際に保存する実装をしてください。

`/blogs`にリクエストが来た際に動作するようにしてください。

`blog/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、新しいブログを作成ボタンを押下してください。  
タイトルと内容を入力して保存ボタンを押下してください。  
一覧画面に遷移して、登録した情報が表示されていたら正常に実装ができております。

# ブログ記事詳細画面表示機能

以下の手順で、ブログ記事の詳細画面に遷移する機能を作成していきましょう。

## Repository（Mapper Interface）

repository パッケージにある BlogMapper.java に、ブログ記事を ID で取得するメソッドを定義します。

メソッド名は findById にしてください。
・引数：int id
・戻り値：Blog オブジェクト

## Mapper XML

Mapper Interface に対応する SQL を resources/mapper/BlogMapper.xml に定義します。

findById メソッドと同じ id を使用してください。

SQL は削除日時 (deleted_at) が NULL のブログ記事を ID 指定で取得する形にします。

## Service

service パッケージに`BlogService.java`を作成してください。

`detail`メソッドを作成し、Repository の`findById`メソッドを呼び出して、ID に対応する`Blog`オブジェクトを返却してください。

## Controller

ブログ詳細画面のリクエストに対応する Controller メソッドを実装してください。

`BlogService`の`detail`メソッドを呼び出し、ブログ記事を取得してください。  
取得したブログ記事をモデルに追加し、`src/main/resources/templates/blog/detail.html`を表示してください。

## 動作確認

`localhost:8080/blogs/{id}` にアクセスし、指定された ID のブログ記事の詳細が表示されることを確認してください。

# ブログ記事更新機能

以下の手順でブログ記事の更新機能を作成していきましょう。

## Repository（Mapper Interface）

repository パッケージの BlogMapper.java に update メソッドを作成してください。

## Mapper XML

Mapper Interface に対応する SQL を resources/mapper/BlogMapper.xml に定義します。

update メソッドと同じ id を使用してください。

SQL では、タイトルと内容を更新し、更新日時を現在時刻に設定します。

## Service

ビジネスロジックを作成してください。

Form から Entity に詰め直して更新するように実装してください。

## Controller（Get リクエスト）

ブログ更新画面のリクエストが来た際に、既存のデータをフォームに表示する実装をしてください。

`/blogs/{id}/edit`にリクエストが来た際に動作するようにしてください。

`src/main/resources/templates/blog/form.html`を表示してください。

## Controller（Post リクエスト）

ブログ更新のリクエストが来た際に保存する実装をしてください。

`/blogs/{id}`にリクエストが来た際に動作するようにしてください。

`blog/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、既存のデータのタイトルを押下して、詳細画面に遷移してください。  
編集ボタンを押下して、更新画面に遷移してください。

タイトルと内容を更新して保存ボタンを押下してください。  
一覧画面に遷移して、更新した情報が表示されていたら正常に実装ができております。

`localhost:8080/blogs/{id}/edit`にアクセスして、フォームにデータが表示されることを確認し、内容を変更して更新してください。  
更新後一覧画面にて内容が更新されていたら正常に実装ができています！

# ブログ記事削除機能

以下の手順でブログ記事の削除機能を作成していきましょう。

## Repository（Mapper Interface）

repository パッケージの BlogMapper.java に delete メソッドを作成してください。

## Mapper XML

Mapper Interface に対応する SQL を resources/mapper/BlogMapper.xml に定義します。

delete メソッドと同じ id を使用してください。

SQL では、指定された ID のレコードの削除日時（deleted_at）を現在時刻に更新するようにします。

## Service

ビジネスロジックを作成してください。

削除リクエストを受け取り、Repository の delete メソッドを呼び出す実装をしてください。

## Controller

削除リクエストが来た際に、削除処理を行う実装をしてください。

`/blogs/{id}/delete`にリクエストが来た際に動作するようにしてください。

削除後は`src/main/resources/templates/blog/list.html`を表示してください。

## 動作確認

`localhost:8080/blogs`にアクセスして、詳細画面に遷移してください。
削除ボタンを押下してください

一覧画面に遷移して、該当の情報が削除されていたら正常に実装ができております。


