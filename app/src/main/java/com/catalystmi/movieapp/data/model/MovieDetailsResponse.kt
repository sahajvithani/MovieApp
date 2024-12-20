package com.catalystmi.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("belongs_to_collection") var belongsToCollection : BelongsToCollection? = BelongsToCollection(),
    @SerializedName("budget") var budget: Int? = null,
    @SerializedName("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("imdb_id") var imdbId: String? = null,
    @SerializedName("origin_country") var originCountry: ArrayList<String> = arrayListOf(),
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_companies") var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    @SerializedName("production_countries") var productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("revenue") var revenue: Int? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("spoken_languages") var spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
)

data class Genres(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)

data class ProductionCompanies(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("logo_path") var logoPath: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("origin_country") var originCountry: String? = null
)

data class ProductionCountries(
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null
)

data class SpokenLanguages(
    @SerializedName("english_name") var englishName: String? = null,
    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("name") var name: String? = null
)

data class BelongsToCollection (

    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("poster_path"   ) var posterPath   : String? = null,
    @SerializedName("backdrop_path" ) var backdropPath : String? = null

)



/*
* {
    "adult": false,
    "backdrop_path": "/c6nouvFYnmNO50WQDLcKMI3p0jA.jpg",
    "belongs_to_collection": {
        "id": 762512,
        "name": "The Lion King (Reboot) Collection",
        "poster_path": "/dGpIRn4Nqi63JO1RlKxjcPbQSAw.jpg",
        "backdrop_path": "/jIgM7YNVft0YGeXsqrh3oG5TWLx.jpg"
    },
    "budget": 200000000,
    "genres": [
        {
            "id": 12,
            "name": "Adventure"
        },
        {
            "id": 10751,
            "name": "Family"
        },
        {
            "id": 18,
            "name": "Drama"
        },
        {
            "id": 16,
            "name": "Animation"
        }
    ],
    "homepage": "https://movies.disney.com/mufasa-the-lion-king",
    "id": 762509,
    "imdb_id": "tt13186482",
    "origin_country": [
        "US"
    ],
    "original_language": "en",
    "original_title": "Mufasa: The Lion King",
    "overview": "Told in flashbacks, Mufasa is an orphaned cub, lost and alone until he meets a sympathetic lion named Taka—the heir to a royal bloodline. The chance meeting sets in motion a journey of misfits searching for their destiny and working together to evade a threatening and deadly foe.",
    "popularity": 1181.385,
    "poster_path": "/lurEK87kukWNaHd0zYnsi3yzJrs.jpg",
    "production_companies": [
        {
            "id": 2,
            "logo_path": "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
            "name": "Walt Disney Pictures",
            "origin_country": "US"
        }
    ],
    "production_countries": [
        {
            "iso_3166_1": "US",
            "name": "United States of America"
        }
    ],
    "release_date": "2024-12-18",
    "revenue": 0,
    "runtime": 118,
    "spoken_languages": [
        {
            "english_name": "English",
            "iso_639_1": "en",
            "name": "English"
        }
    ],
    "status": "Released",
    "tagline": "The story of an orphan who would be king.",
    "title": "Mufasa: The Lion King",
    "video": false,
    "vote_average": 7.2,
    "vote_count": 19
}*/
/*

{
    "adult": false,
    "backdrop_path": "/4cp40IyTpFfsT2IKpl0YlUkMBIR.jpg",
    "belongs_to_collection": null,
    "budget": 6000000,
    "genres": [
    {
        "id": 10749,
        "name": "Romance"
    },
    {
        "id": 35,
        "name": "Comedy"
    },
    {
        "id": 18,
        "name": "Drama"
    }
    ],
    "homepage": "https://anora.film",
    "id": 1064213,
    "imdb_id": "tt28607951",
    "origin_country": [
    "US"
    ],
    "original_language": "en",
    "original_title": "Anora",
    "overview": "A young sex worker from Brooklyn gets her chance at a Cinderella story when she meets and impulsively marries the son of an oligarch. Once the news reaches Russia, her fairytale is threatened as his parents set out to get the marriage annulled.",
    "popularity": 223.013,
    "poster_path": "/7MrgIUeq0DD2iF7GR6wqJfYZNeC.jpg",
    "production_companies": [
    {
        "id": 88152,
        "logo_path": null,
        "name": "Cre Film",
        "origin_country": "US"
    },
    {
        "id": 7493,
        "logo_path": "/fHitYiGUCkRafgt6VPYQlXWLkdp.png",
        "name": "FilmNation Entertainment",
        "origin_country": "US"
    }
    ],
    "production_countries": [
    {
        "iso_3166_1": "US",
        "name": "United States of America"
    }
    ],
    "release_date": "2024-10-14",
    "revenue": 25467529,
    "runtime": 139,
    "spoken_languages": [
    {
        "english_name": "Armenian",
        "iso_639_1": "hy",
        "name": ""
    },
    {
        "english_name": "English",
        "iso_639_1": "en",
        "name": "English"
    },
    {
        "english_name": "Russian",
        "iso_639_1": "ru",
        "name": "Pусский"
    },
    {
        "english_name": "Vietnamese",
        "iso_639_1": "vi",
        "name": "Tiếng Việt"
    }
    ],
    "status": "Released",
    "tagline": "Love is a hustle.",
    "title": "Anora",
    "video": false,
    "vote_average": 7.3,
    "vote_count": 376
}*/
