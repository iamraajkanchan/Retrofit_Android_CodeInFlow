package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{
    private lateinit var txtResult : TextView
    private lateinit var jsonPlaceholderAPI : JSONPlaceholderAPI

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtResult = findViewById(R.id.txt_result)
        establishRetrofitConnection()
    }

    private fun establishRetrofitConnection()
    {
        val gson : Gson = GsonBuilder().serializeNulls().create()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient : OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()
        jsonPlaceholderAPI = retrofit.create(JSONPlaceholderAPI::class.java)
        getPosts()
        //        getComments()
        //        getCommentsWithPostId()
        //        getSortedCommentsWithPostId()
        //        getSortedCommentsWithPostIds()
        //        getSortedCommentsWithParameters()
        //        getCommentsWithURL()
        //        createPost()
        //        createPostWithField()
        //        createPostWithMap()
        //        updatePost()
        //        deletePost()
    }

    private fun getPosts()
    {
        val call : Call<List<Post>> = jsonPlaceholderAPI.getPosts()
        call.enqueue(object : Callback<List<Post>>
        {
            override fun onResponse(call : Call<List<Post>> , response : Response<List<Post>>)
            {
                println("MainActivity :: getPosts :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val posts : List<Post> = response.body() !!
                for (post in posts)
                {
                    var context : String = ""
                    context += "ID: ${post.id}\n"
                    context += "USER_ID: ${post.userId}\n"
                    context += "TITLE: ${post.title}\n"
                    context += "TEXT: ${post.text}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Post>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getComments()
    {
        val call : Call<List<Comment>> = jsonPlaceholderAPI.getComments(3)
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getComments :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getCommentsWithPostId()
    {
        val call : Call<List<Comment>> = jsonPlaceholderAPI.getCommentsWithPostId(10)
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getCommentsWithPostId :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getSortedCommentsWithPostId()
    {
        val call : Call<List<Comment>> = jsonPlaceholderAPI.getSortedCommentsWithPostId(null , "id" , "null")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getSortedCommentsWithPostId :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getSortedCommentsWithPostIds()
    {
        val call : Call<List<Comment>> = jsonPlaceholderAPI.getSortedCommentsWithPostIds(listOf(1 , 3 , 5) , "id" , "null")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getSortedCommentsWithPostIds :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getSortedCommentsWithParameters()
    {
        val parameters = HashMap<String , String>().apply {
            put("_order" , "asc")
            put("_sort" , "id")
            put("postId" , "1")
        } as Map<String , String>

        val call : Call<List<Comment>> = jsonPlaceholderAPI.getSortedCommentsWithParameters(parameters)
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getSortedCommentsWithPostIds :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun getCommentsWithURL()
    {
        val call : Call<List<Comment>> = jsonPlaceholderAPI.getCommentsWithURL("posts/1/comments")
        //        You can pass null in these arguments to surpass the conditions
        call.enqueue(object : Callback<List<Comment>>
        {
            override fun onResponse(call : Call<List<Comment>> , response : Response<List<Comment>>)
            {
                println("MainActivity :: getCommentsWithURL :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code: ${response.code()}"
                    return
                }
                val comments : List<Comment> = response.body() !!
                for (comment in comments)
                {
                    var context : String = ""
                    context += "ID: ${comment.id}\n"
                    context += "POST_ID: ${comment.postId}\n"
                    context += "NAME: ${comment.name}\n"
                    context += "EMAIL: ${comment.email}\n"
                    context += "COMMENT: ${comment.comment}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<List<Comment>> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun createPost()
    {
        val post = Post(12341 , "Learn Retrofit" , "Learning Retrofit and Kotlin at the same time")
        val call : Call<Post> = jsonPlaceholderAPI.createPost(post)
        call.enqueue(object : Callback<Post>
        {
            override fun onResponse(call : Call<Post> , response : Response<Post>)
            {
                println("MainActivity :: createPost :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code ${response.code()}"
                    return
                }
                val postResponse : Post? = response.body()
                var context = ""
                context += "ID: ${postResponse !!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "TEXT: ${postResponse.text}\n" + "\n"
                txtResult.append(context)
            }

            override fun onFailure(call : Call<Post> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun createPostWithField()
    {
        val call : Call<Post> = jsonPlaceholderAPI.createPostWithField(12342 , "Learn Retrofit Post" , "Learning uses of @FormURLEncoded and @Field")
        call.enqueue(object : Callback<Post>
        {
            override fun onResponse(call : Call<Post> , response : Response<Post>)
            {
                println("MainActivity :: createPostWithField :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code ${response.code()}"
                    return
                }
                val postResponse : Post? = response.body()
                var context = ""
                context += "ID: ${postResponse !!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "TEXT: ${postResponse.text}\n" + "\n"
                txtResult.append(context)
            }

            override fun onFailure(call : Call<Post> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun createPostWithMap()
    {
        val inputFields = HashMap<String , String>().apply {
            put("userId" , "12343")
            put("title" , "Learning Retrofit Post")
            put("body" , "Learning @FieldMap, HashMap, Map and casting of Hashmap to Map")
        } as Map<String , String>
        val call : Call<Post> = jsonPlaceholderAPI.createPostWithMap(inputFields)
        call.enqueue(object : Callback<Post>
        {
            override fun onResponse(call : Call<Post> , response : Response<Post>)
            {
                println("MainActivity :: createPostWithMap :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code : ${response.code()}"
                    return
                }
                val postResponse : Post? = response.body()
                var context = ""
                context += "ID: ${postResponse !!.id}\n"
                context += "USER_ID: ${postResponse.userId}\n"
                context += "TITLE: ${postResponse.title}\n"
                context += "BODY: ${postResponse.text}\n" + "\n"
                txtResult.append(context)
            }

            override fun onFailure(call : Call<Post> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun updatePost()
    {
        val post = Post(6 , "Learning Retrofit Put" , "Learning @PUT annotation with @PATH and @BODY annotation")
        val call : Call<Post> = jsonPlaceholderAPI.putPost(5 , post)
        call.enqueue(object : Callback<Post>
        {
            override fun onResponse(call : Call<Post> , response : Response<Post>)
            {
                println("MainActivity :: putPost :: response : $response")
                if (! response.isSuccessful)
                {
                    txtResult.text = "Code : ${response.code()}"
                    return
                }
                else
                {
                    val putResponse : Post? = response.body()
                    var context = ""
                    context += "ID: ${putResponse !!.id}\n"
                    context += "USER_ID: ${putResponse.userId}\n"
                    context += "TITLE: ${putResponse.title}\n"
                    context += "BODY: ${putResponse.text}\n" + "\n"
                    txtResult.append(context)
                }
            }

            override fun onFailure(call : Call<Post> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

    private fun deletePost()
    {
        val call : Call<Post> = jsonPlaceholderAPI.deletePost(5)
        call.enqueue(object : Callback<Post>
        {
            override fun onResponse(call : Call<Post> , response : Response<Post>)
            {
                println("MainActivity :: deletePost :: response : $response")
                txtResult.text = "Code : ${response.code()}"
            }

            override fun onFailure(call : Call<Post> , t : Throwable)
            {
                txtResult.text = t.message
            }
        })
    }

}
