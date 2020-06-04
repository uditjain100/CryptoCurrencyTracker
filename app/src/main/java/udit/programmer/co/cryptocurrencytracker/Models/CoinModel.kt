package udit.programmer.co.cryptocurrencytracker.Models

data class CoinModel(
	val data: List<DataItem?>? = null,
	val status: Status? = null
)

data class Status(
	val errorMessage: Any? = null,
	val elapsed: Int? = null,
	val creditCount: Int? = null,
	val errorCode: Int? = null,
	val timestamp: String? = null,
	val notice: Any? = null
)

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

data class Platform(
	val symbol: String? = null,
	val name: String? = null,
	val tokenAddress: String? = null,
	val id: Int? = null,
	val slug: String? = null
)

data class Quote(
	val uSD: USD? = null
)

data class USD(
	val percentChange1h: Double? = null,
	val lastUpdated: String? = null,
	val percentChange24h: Double? = null,
	val marketCap: Double? = null,
	val price: Double? = null,
	val volume24h: Double? = null,
	val percentChange7d: Double? = null
)

