package com.example.navigationhelp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //below is the code for context matlab selected item wala popup and etc menu

        registerForContextMenu(btnContextMenu)
        btnContextMenu.setOnClickListener{
            openContextMenu(btnContextMenu)
            true
        }
        btnPopMenu.setOnClickListener{
            val popup= PopupMenu(this@MainActivity, btnPopMenu)
            //inflating the popup
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener {
              if(it.itemId == R.id.one) {
                  Toast.makeText(applicationContext, "One", Toast.LENGTH_SHORT).show()
              }else {
                  Toast.makeText(applicationContext, it.title, Toast.LENGTH_SHORT).show()
              }
                true
            }
            popup.show()
        }

        btnPopupMenuGroup.setOnClickListener {

            val popup = PopupMenu(this@MainActivity, btnPopupMenuGroup)
            //Inflating the Popup using xml file
            popup.menuInflater.inflate(R.menu.popup_menu_group, popup.menu)


            popup.setOnMenuItemClickListener {
                if (it.itemId == R.id.four && it.isChecked) {
                    Toast.makeText(applicationContext, "Four. Was Checked", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(applicationContext, it.title, Toast.LENGTH_SHORT).show()
                }
                true
            }
            popup.show()
        }
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            super.onCreateContextMenu(menu, v, menuInfo)
            menu?.setHeaderTitle("Context Menu")
            menu?.add(0, v?.id!!, 0, "Call")
            menu?.add(0, v?.id!!, 1, "SMS")
            menu?.add(1, v?.id!!, 0, "Search")

        }

        //here is the code for menu bar menu item goda
        override fun onCreateOptionsMenu(menu: Menu):Boolean {
            val inflater= menuInflater
            inflater.inflate(R.menu.menu_main, menu)
            return true
        }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.title) {
            "call" -> {
                Toast.makeText(applicationContext, "Call", Toast.LENGTH_LONG).show()
                true
            }
            "SMS" -> {
                Toast.makeText(applicationContext, "SMS", Toast.LENGTH_LONG).show()
                true
            }
            "Search" -> {
                Toast.makeText(applicationContext, "Search", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add ->{
                Log.d("API123","done")
                return true
            }
            R.id.call ->{
                Log.d("API123","done")
               return true
            }
            R.id.compass ->{
                //Log.d("API123","done")
                // startActivity(Intent(this, openActivity::class.java)
//
//                    val intent = Intent(this, openActivity::class.java).apply {
//                }
                startActivity(Intent(this, openActivity::class.java))
                return true
            }
            R.id.agenda ->{
                Log.d("API123", "done")
                return true
            }
            else ->return super.onOptionsItemSelected(item)
        }

    }



}

//override use krte hain agar fun local scope ke andar na ho oncreate ke