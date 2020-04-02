package br.com.myfinances.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import br.com.myfinances.NavHandler
import br.com.myfinances.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_bar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var navController: NavController
    private lateinit var navHandler: NavHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configToolbar()
        configNavigation()
    }

    private fun configToolbar(){
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun configNavigation(){
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_main, navController)
        nav_main.setNavigationItemSelectedListener(this)
        navHandler = NavHandler(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer_layout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer_layout.closeDrawers()

        when(item.itemId){
            R.id.categories -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_category)
            R.id.accounts -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_account)
            R.id.entries -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_entry)
            R.id.dashboard -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_dashboard)
            R.id.reports -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_report)
            R.id.configuration -> navHandler.navigate(R.id.home_fragment, R.id.action_home_to_config)
            R.id.exit -> finishAndRemoveTask()
        }

        return true
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
}