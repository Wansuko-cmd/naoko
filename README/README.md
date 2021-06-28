# NAOKO

## What is this?

This is `News Api wrapper fOr KOtlin.`


## How to use?

### 1. Implement by Gradle

### 2. Make a Naoko Instance

```kotlin

/**
 * Make Naoko Instance
 * 
 * # apiKey 
 * Input your News API Key
 * 
 * # country 
 * Input your default target country.
 * Choose your country from Country Enum which I prepared.
 * 
 */
val naoko = Naoko.build(
    apiKey = "YOUR_API_KEY",
    country = Country.YOUR_COUNTRY
)

```

### 3. Just use!

Make sure all method is suspend function.

```kotlin

/**
 * In this case this library will correct news with the information below
 * 
 * - News API: Top Headlines(https://newsapi.org/docs/endpoints/top-headlines)
 * - country = The USA
 * - category = Business
 * 
 */

runBlocking{
    val news = naoko.getTopHeadlines(
        country = Country.US,
        category = Category.BUSINESS
    )
}


/**
 * In this case this library will correct news with the information below
 * 
 * - News API: Everything(https://newsapi.org/docs/endpoints/everything)
 * - q = kotlin(News about kotlin will be collected)
 * - domains = bbc and techcrunch
 * - to = 2021-6-15 21:30:50(News published before this time will be collected)
 * - sortBy = Relevancy
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

Notice that when argument is null, the value will be ignored.

Sometimes, in the type row, (Enum) in some cases.

### Everything
Search through millions of articles from over 80,000 large and small news sources and blogs.

This endpoint suits article discovery and analysis.
(https://newsapi.org/docs/endpoints/everything)

| argument |  type  |  remind  |
| ---- | ---- | ---- |
|  q  |  String?  |
|  qInTitle  |  String?  |
| sources | String? |
| domains | List<String>? | Make a list of domains |
| excludeDomains | List<String>? | Make a list of not-include domains |
| from | LocalDateTime? |
| to | LocalDateTime? |
| language | Language(Enum)? |  |
| sortBy | SortBy(Enum)? |
| pageSize | Int? |
| page | Int? |
