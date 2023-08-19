package com.example.retrofitexample.repository

import com.example.retrofitexample.retrofit.ApiService
import com.example.retrofitexample.model.Comment
import com.example.retrofitexample.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(private val apiService: ApiService) {
    fun getPosts(): String {
        var data = StringBuilder()
        val call: Call<List<Post>> = apiService.getPosts()
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                println("AppRepository :: getPosts :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val posts: List<Post> = response.body() ?: emptyList()
                for (post in posts) {
                    var context: String = ""
                    context += "ID: ${post.id}\n"
                    context += "USER_ID: ${post.userId}\n"
                    context += "TITLE: ${post.title}\n"
                    context += "TEXT: ${post.text}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getComments(): String? {
        var data = StringBuilder()
        val call: Call<List<Comment>> = apiService.getComments(3)
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getComments :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getCommentsWithPostId(): String {
        var data = StringBuilder()
        val call: Call<List<Comment>> = apiService.getCommentsWithPostId(10)
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getCommentsWithPostId :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getSortedCommentsWithPostId(): String {
        var data = StringBuilder()
        val call: Call<List<Comment>> =
            apiService.getSortedCommentsWithPostId(null, "id", "null")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getSortedCommentsWithPostId :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getSortedCommentsWithPostIds(): String {
        var data = StringBuilder()
        val call: Call<List<Comment>> =
            apiService.getSortedCommentsWithPostIds(listOf(1, 3, 5), "id", "null")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getSortedCommentsWithPostIds :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                    return
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getSortedCommentsWithParameters() : String {
        var data = StringBuilder()
        val parameters = HashMap<String, String>().apply {
            put("_order", "asc")
            put("_sort", "id")
            put("postId", "1")
        } as Map<String, String>

        val call: Call<List<Comment>> =
            apiService.getSortedCommentsWithParameters(parameters)
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getSortedCommentsWithPostIds :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun getCommentsWithURL() : String {
        var data = StringBuilder()
        val call: Call<List<Comment>> = apiService.getCommentsWithURL("posts/1/comments")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                println("MainActivity :: getCommentsWithURL :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code: ${response.code()}")
                }
                val comments: List<Comment> = response.body()!!
                for (comment in comments) {
                    var context: String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun createPost() : String {
        var data = StringBuilder()
        val post = Post(12341, "Learn Retrofit", "Learning Retrofit and Kotlin at the same time")
        val call: Call<Post> = apiService.createPost(post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                println("MainActivity :: createPost :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code ${response.code()}")
                }
                val postResponse: Post? = response.body()
                var context = ""
                context += "ID: ${postResponse!!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "TEXT: ${postResponse.text}\n" + "\n"
                data.append(context)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun createPostWithField() : String {
        var data = StringBuilder()
        val call: Call<Post> = apiService.createPostWithField(
            12342,
            "Learn Retrofit Post",
            "Learning uses of @FormURLEncoded and @Field"
        )
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                println("MainActivity :: createPostWithField :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code ${response.code()}")
                }
                val postResponse: Post? = response.body()
                var context = ""
                context += "ID: ${postResponse!!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "TEXT: ${postResponse.text}\n" + "\n"
                data.append(context)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun createPostWithMap(): String {
        var data = StringBuilder()
        val inputFields = HashMap<String, String>().apply {
            put("userId", "12343")
            put("title", "Learning Retrofit Post")
            put("body", "Learning @FieldMap, HashMap, Map and casting of Hashmap to Map")
        } as Map<String, String>
        val call: Call<Post> = apiService.createPostWithMap(inputFields)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                println("MainActivity :: createPostWithMap :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code : ${response.code()}")
                }
                val postResponse: Post? = response.body()
                var context = ""
                context += "ID: ${postResponse!!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "BODY: ${postResponse.text}\n" + "\n"
                data.append(context)
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun updatePost(): String {
        var data = StringBuilder()
        val post = Post(
            6,
            "Learning Retrofit Put",
            "Learning @PUT annotation with @PATH and @BODY annotation"
        )
        val call: Call<Post> = apiService.putPost(5, post)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                println("MainActivity :: putPost :: response : $response")
                if (!response.isSuccessful) {
                    data = StringBuilder("Code : ${response.code()}")
                } else {
                    val putResponse: Post? = response.body()
                    var context = ""
                    context += "ID: ${putResponse!!.id}\n"
                    context += "USER_ID: ${putResponse.userId}\n"
                    context += "TITLE: ${putResponse.title}\n"
                    context += "BODY: ${putResponse.text}\n" + "\n"
                    data.append(context)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                data = StringBuilder(t.message ?: SERVER_ERROR)
            }
        })
        return data.toString()
    }

    fun deletePost(): String {
        var data = ""
        val call: Call<Post> = apiService.deletePost(5)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                println("MainActivity :: deletePost :: response : $response")
                data = "Code : ${response.code()}"
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                data = t.message ?: SERVER_ERROR
            }
        })
        return data
    }

    companion object {
        private const val SERVER_ERROR: String = "Server Not Responding!"
    }
}