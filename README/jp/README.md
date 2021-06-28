# NAOKO

## Naokoとは

Naokoとは、News APIをKotlinで使うためのライブラリで、
`News Api wrapper fOr KOtlin.`の略称です。



## 使い方

### 1. Gradle等に記述

### 2. Naokoのインスタンスを作成

```kotlin

/**
 * 
 * # apiKey 
 * News APIのKeyを設定
 * 
 * # country 
 * どこの国の記事を集めるかを設定（デフォルト値になります）
 * このライブラリにて用意されているEnum型（後述）を利用してください
 * 
 */
val naoko = Naoko.build(
    apiKey = "YOUR_API_KEY",
    country = Country.JP
)

```

### 3. 使う

インスタンスにあるメソッドを利用します。
どのメソッドもsuspend関数です。

```kotlin

/**
 * この場合、以下の情報が集められます
 * 
 * - News API: Top Headlines(https://newsapi.org/docs/endpoints/top-headlines)
 * - 対象の国： The USA
 * - 対象のカテゴリー： Business
 * 
 */

runBlocking{
    val news = naoko.getTopHeadlines(
        country = Country.US,
        category = Category.BUSINESS
    )
}


/**
 * この場合、以下の情報が集められます
 * 
 * - News API: Everything(https://newsapi.org/docs/endpoints/everything)
 * - クエリ： kotlin(Kotlinに関するニュースが集められます)
 * - ドメイン：BBCとTechcrunchのニュースが集められます
 * - to = 2021-6-15 21:30:50(この時間より前に公開された記事が集められます)
 * - sortBy = Relevancy(クエリで指定した値に最も近いもの順に集められます)
 * 
 */
runBlocking{
    val news = naoko.getEverything(
        q = "kotlin",
        domains = listOf("bbc.co.uk", "techcrunch.com"),
        to = LocalDateTime.parse("2021-06-15T21:30:50"),
        sortBy = SortBy.RELEVANCY
    )
}
```

## Functions

基本的に引数の値を設定しなかったり、nullを代入した場合、検索条件から外されます。

引数の型のところに(Enum)と書かれている場合は、ライブラリで用意したEnum型が利用されています。
詳しくはEnumの章を確認して下さい

### Everything
80,000以上の大小のニュースソースやブログから数百万の記事を検索できます。

このエンドポイントは、記事の発見と分析に適しています。
(https://newsapi.org/docs/endpoints/everything)

この機能を使う場合は`getEverything`関数を利用します。

```kotlin

naoko.getEverything()

```


| 引数 |  型  |  備考  |
| ---- | ---- | ---- |
|  q  |  String?  |
|  qInTitle  |  String?  |
| sources | String? |
| domains | List<String>? | 収集対象のドメインの名前をリストにしてください |
| excludeDomains | List<String>? | 収集対象外のドメインの名前をリストにしてください |
| from | LocalDateTime? |
| to | LocalDateTime? |
| language | Language(Enum)? | ライブラリで宣言されたEnum型`Language`を利用 |
| sortBy | SortBy(Enum)? | ライブラリで宣言されたEnum型`SortBy`を利用 |
| pageSize | Int? |
| page | Int? |

この関数を実行することで受け取れる型は以下の通りです

名称：NaokoArticle

| メンバ変数 | 型 | 備考 |
|----|----|----|
| status | string |
| totalResults | Int |
| articles | List<Article> | 以下で説明する`Article`型のリストです|

名称：Article

| メンバ変数 | 型 | 備考 |
|----|----|----|
| source | Source? | 以下で説明するSource型です |
| author | String? |
| title | String? |
| description | String? |
| url | String? |
| urlToImage | String? |
| publishedAt | String? |
| content | String? |

名称：Source

| メンバ変数 | 型 | 備考 |
|----|----|----|
| id | String? |
| name | String? |

NewsAPIでは一部の返り値がnullになることも多いため、基本的にnull許容型になっています。


それぞれの値の詳しい説明は以下のURLを参照してください

https://newsapi.org/docs/endpoints/everything


### TopHeadlines

このエンドポイントでは、国、国の特定のカテゴリー、単一のソース、または複数のソースのトップニュースや
速報のヘッドラインをライブで提供します。

また、キーワードによる検索も可能です。記事は公開日の古いものから順に表示されます。
(https://newsapi.org/docs/endpoints/top-headlines)

この機能を使う場合は`getTopHeadlines`関数を利用します。

```kotlin

naoko.getTopHeadlines()

```

| 引数 |  型  |  備考  |
| ---- | ---- | ---- |
|  country  |  Country(Enum)?  | ライブラリで宣言されたEnum型`Country`を利用<br>また、nullが設定され場合、デフォルト値を適用 |
|  category  |  Category?  | ライブラリで宣言されたEnum型`Category`を利用 |
|  q  |  String?  |
| pageSize | Int? |
| page | Int? |


もしくは

| 引数 |  型  |  備考  |
| ---- | ---- | ---- |
| sources | String? |
|  q  |  String?  |
| pageSize | Int? |
| page | Int? |

のどちらかを利用してください。
渡された引数の型や引数の名前を指定することで自動で定まります。


なお、戻り値は`Everything`と同じ、`NaokoArticle`です。

それぞれの値の詳しい説明は以下のURLを参照してください

https://newsapi.org/docs/endpoints/top-headlines


### Sources

このエンドポイントは、トップヘッドライン（/v2/top-headlines）が利用可能なニュースパブリッシャーのサブセットを返します。

これは主に、APIで利用可能なパブリッシャーを追跡するために使用できる便利なエンドポイントで、
そのままユーザーにパイプで送ることができます。
(https://newsapi.org/docs/endpoints/sources)

この機能を使う場合は`getSources`関数を利用します。

```kotlin

naoko.getSources()

```

| 引数 |  型  |  備考  |
| ---- | ---- | ---- |
|  category  |  Category?  | ライブラリで宣言されたEnum型`Category`を利用 |
| language | Language(Enum)? | ライブラリで宣言されたEnum型`Language`を利用 |
|  country  |  Country(Enum)?  | ライブラリで宣言されたEnum型`Country`を利用<br>また、nullが設定され場合、デフォルト値を適用 |


この関数を実行することで受け取れる型は以下の通りです

名称：NaokoSources

| メンバ変数 | 型 | 備考 |
|----|----|----|
| status | String |
| sources | List<Sources> | `Everything`の章で説明した`Source`とは違います |

名称：Sources

| メンバ変数 | 型 | 備考 |
|----|----|----|
| id | String |
| name | String |
| description | String |
| url | String |
| category | String |
| language | String |
| country | String |


それぞれの値の詳しい説明は以下のURLを参照してください

https://newsapi.org/docs/endpoints/sources


## Enum

このライブラリでは、特定の値以外受け付けない、といったところではEnumを利用しています。

以下ではどのようなものがあるかを解説します

### Category

カテゴリーを指定する際に利用します。

| 名称 | 値 |
|----|----|
|BUSINESS|
|ENTERTAINMENT|
|GENERAL|
|HEALTH|
|SCIENCE|
|SPORTS|
|TECHNOLOGY|

### Country

国を指定する際に利用します

|名称|
|----|
| AE |
| AR |
| AT |
| AU |
| BE |
| BG |
| BR |
| CA |
| CH |
| CN |
| CO |
| CU |
| CZ |
| DE |
| EG |
| FR |
| GB |
| GR |
| HK |
| HU |
| ID |
| IE |
| IL |
| IN |
| IT |
| JP |
| KR |
| LT |
| LV |
| MA |
| MX |
| MY |
| NG |
| NL |
| NO |
| NZ |
| PH |
| PL |
| PT |
| RO |
| RS |
| RU |
| SA |
| SE |
| SG |
| SI |
| SK |
| TH |
| TR |
| TW |
| UA |
| US |
| VE |
| ZA |

### Language

言語を指定する際に利用します

|名称|
|----|
| AR |
| DE |
| EN |
| ES |
| FR |
| HE |
| IT |
| NL |
| NO |
| PT |
| RU |
| SE |
| UD |
| ZH |


### SortBy

並び方を指定する際に利用します

|名称|
|----|
|RELEVANCY|
|POPULARITY|
|PUBLISHED_AT|


これらは以下のように使います

```kotlin

/**
 * 日本のニュースでかつ、ビジネスのジャンルのもののみを集める
 */
runBlocking{
    val news = naoko.getTopHeadlines(
        country = Country.JP,
        category = Category.BUSINESS,
    )
}

```

また、文字列からこれらのEnum型に変換することのできる`serializer`関数も用意しています

```kotlin
runBlocking{
    country = "jp"
    naoko.getTopHeadlines(
        country = country?.let { Country.serializer(it) }
    )
}
```

この際、入れる文字列は、指定したい値を小文字にしたもの（CategoryのBUSINESSを指定したいなら`Category.serializer("business")`）
のように指定してください