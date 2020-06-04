package udit.programmer.co.cryptocurrencytracker.Models

data class DataItem(
    val symbol: String? = null,
    val circulatingSupply: Int? = null,
    val lastUpdated: String? = null,
    val totalSupply: Int? = null,
    val cmcRank: Int? = null,
    val platform: Any? = null,
    val tags: List<String?>? = null,
    val dateAdded: String? = null,
    val quote: Quote? = null,
    val numMarketPairs: Int? = null,
    val name: String? = null,
    val maxSupply: Int? = null,
    val id: Int? = null,
    val slug: String? = null
)