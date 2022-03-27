package com.avin.news.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.avin.news.adapter.NewsListAdapter
import com.avin.news.databinding.ActivityMainBinding
import com.avin.news.model.NewsData
import com.avin.news.viewmodel.NewsViewModel


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = com.avin.news.R.layout.activity_main;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarText("Top News")
        hideBack()
        getNewsApi()
    }

    private fun getNewsApi() {
        val newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel.getNewsList().observe(this, Observer { newsData ->
            hideProgress()
            showData()
            setAdapter(newsData.articles);
        })
    }
    // setting adapter
    private fun setAdapter(newsList: ArrayList<NewsData>) {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = NewsListAdapter(this@MainActivity, newsList)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

    // onclick adapter
            (adapter as NewsListAdapter).onItemClick = { url ->
                val i: Intent = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra("url", url)
                startActivity(i)
            }
        }


    }

}
