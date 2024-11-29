package com.kuzmin.fooddeliverytestapp.ui

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.navigation.NavigationView
import com.kuzmin.fooddeliverytestapp.R
import com.kuzmin.fooddeliverytestapp.databinding.ActivityMainBinding
import com.kuzmin.fooddeliverytestapp.domain.model.Result
import com.kuzmin.fooddeliverytestapp.domain.model.Result.Error
import com.kuzmin.fooddeliverytestapp.domain.model.Result.UpdateAddressSuccess
import com.kuzmin.fooddeliverytestapp.domain.model.address.Location
import com.kuzmin.fooddeliverytestapp.domain.model.food.CatalogItem
import com.kuzmin.fooddeliverytestapp.extension.checkNetworkConnection
import com.kuzmin.fooddeliverytestapp.extension.dpToPx
import com.kuzmin.fooddeliverytestapp.extension.hasRequiredRuntimePermissions
import com.kuzmin.fooddeliverytestapp.extension.requestLocationPermission
import com.kuzmin.fooddeliverytestapp.extension.showToast
import com.kuzmin.fooddeliverytestapp.ui.adapters.BannerListAdapter
import com.kuzmin.fooddeliverytestapp.ui.adapters.CategoryListAdapter
import com.kuzmin.fooddeliverytestapp.ui.adapters.DiscountListAdapter
import com.kuzmin.fooddeliverytestapp.ui.fragments.BottomSheetAddressSearchFragment
import com.kuzmin.fooddeliverytestapp.ui.viewmodels.MainActivityViewModel
import com.kuzmin.fooddeliverytestapp.util.FoodDefaultData
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var  connectivityManager: ConnectivityManager

    private val categoryListAdapter: CategoryListAdapter by lazy {
        CategoryListAdapter()
    }

    private val bannerListAdapter: BannerListAdapter by lazy {
        BannerListAdapter()
    }

    private val discountListAdapter: DiscountListAdapter by lazy {
        DiscountListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()

        setupDrawer()

        setupRvAdapters()

        fillCatalog()

        connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        checkNetworkConnection(connectivityManager)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermissionAndGetLocation()

        val navigationView: NavigationView = binding.nvNav
        setNavigationItemClockListener(navigationView)

        setupCustomMenuItem()

        viewModel.loadAddress()

        viewModel.result.observe(this) {
            handleResultObservation(it)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.tb)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(
            this, binding.dlDrawer, binding.tb,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.dlDrawer.addDrawerListener(toggle)
        toggle.syncState()

        binding.clAddressTb.setOnClickListener {
            showBottomSheetDialogFragment()
        }
    }

    private fun handleResultObservation(result: Result) {
        when (result) {
            is UpdateAddressSuccess -> {
                binding.tvSubtitle.text = result.address
            }
            is Error -> {
                showToast(result.message)
                Log.d("ERROR", "Error: ${result.message}")
            }
        }
    }

    private fun setupRvAdapters() {
        with(binding) {
            rvCategory.adapter = categoryListAdapter
            categoryListAdapter.submitList(FoodDefaultData.getCategories(resources))

            rvBanner.adapter = bannerListAdapter
            bannerListAdapter.submitList(FoodDefaultData.getBanners(resources))

            rvDiscount.adapter = discountListAdapter
            discountListAdapter.submitList(FoodDefaultData.getDiscounts(resources))
        }
    }

    private fun showBottomSheetDialogFragment() {
        BottomSheetAddressSearchFragment().show(supportFragmentManager, BottomSheetAddressSearchFragment.TAG);
    }

    private fun setupDrawer() {
        val logo = BitmapFactory.decodeResource(resources, R.drawable.user_face)
        val logoRounded = RoundedBitmapDrawableFactory.create(resources, logo).apply {
            cornerRadius = dpToPx(49)
        }

        binding.nvNav.getHeaderView(0).apply {
            findViewById<ImageView>(R.id.header_image).setImageDrawable(logoRounded)
        }
    }

    private fun setupCustomMenuItem() {
        val menu = binding.nvNav.menu

        val menuItem = menu.findItem(R.id.nav_payment)
        menuItem.setActionView(R.layout.custom_menu_item)

        val menuItemView = menuItem.actionView
        menuItemView?.findViewById<TextView>(R.id.menu_item_title)?.apply {
            text = getString(R.string.title_payment)
        }

        menuItemView?.findViewById<TextView>(R.id.menu_item_subtitle)?.apply {
            text = String.format(getString(R.string.subtitle_card_number), "1234")
        }

        menuItemView?.invalidate()
    }

    private fun setNavigationItemClockListener(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_addreses -> showToast(R.string.title_addresses)
                R.id.nav_orders -> showToast(R.string.title_orders)
                R.id.nav_settings -> showToast(R.string.title_settings)
                R.id.nav_payment -> showToast(R.string.title_payment)
                R.id.nav_about -> showToast(R.string.title_about_us)
                R.id.nav_news -> showToast(R.string.title_news)
                R.id.nav_cupons -> showToast(R.string.title_cupons)
                R.id.nav_invite -> showToast(R.string.title_invite)
            }
            binding.dlDrawer.closeDrawers()
            true
        }
    }

    private fun checkLocationPermissionAndGetLocation() {
        if (hasRequiredRuntimePermissions()) {
            getLastKnownLocation()
        } else {
            requestLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        var locationData: Location? = null
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: android.location.Location? ->
                location?.let {
                    locationData = Location(it.latitude, it.longitude)

                    viewModel.storeLocationData(locationData!!)
                } ?: showToast(R.string.location_is_not_available)
            }
            .addOnFailureListener {
                showToast(R.string.failed_get_location)
            }
        return locationData
    }

    private fun fillCatalog() {
        val gridLayout = binding.glCatalog
        val catalogItems = FoodDefaultData.getCatalog(resources)

        for (i in 0..8) {
            addCatalogItem(gridLayout, catalogItems[i])
        }
    }

    private fun addCatalogItem(gridLayout: GridLayout, catalogItem: CatalogItem) {
        layoutInflater.inflate(R.layout.item_catalog, gridLayout, false).apply {
            rootView.background = ShapeDrawable().apply {
                shape = RoundRectShape(floatArrayOf(16f, 16f, 16f, 16f, 16f, 16f, 16f, 16f), null, null)
                paint.color = getColor(catalogItem.colorId)
            }

            layoutParams = GridLayout.LayoutParams(
                GridLayout.spec(GridLayout.UNDEFINED, 1f),
                GridLayout.spec(GridLayout.UNDEFINED, 1f)
            ).also {
                it.width = dpToPx(108).toInt()
            }
            findViewById<TextView>(R.id.tv_catalog_title).text = catalogItem.title
            findViewById<ImageView>(R.id.iv_catalog).setImageResource(catalogItem.pictureId)

            rootView.setOnClickListener {
                Log.d("TAG", "Catalog item clicked: ${catalogItem.title}")
            }
            gridLayout.addView(this)
        }
    }
}