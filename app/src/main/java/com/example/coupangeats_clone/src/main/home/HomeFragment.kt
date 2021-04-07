package com.example.coupangeats_clone.src.main.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass.Companion.TAG
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.coupangeats_clone.databinding.FragmentHomeBinding
import com.example.coupangeats_clone.src.main.home.auto_scroll_view.AutoScrollAdapter
import com.example.coupangeats_clone.src.main.home.category.CategoryRecyclerViewAdapter
import com.example.coupangeats_clone.src.main.home.small_store.SmallStoreRecyclerViewAdapter
import com.example.coupangeats_clone.src.main.home.store.StoreRecyclerViewAdapter
import com.example.coupangeats_clone.src.main.address.SetAddressActivity
import com.example.coupangeats_clone.src.main.address.api.GetRepresentAddressService
import com.example.coupangeats_clone.src.main.address.api.GetRepresentAddressView
import com.example.coupangeats_clone.src.main.detail.DetailStoreActivity
import com.example.coupangeats_clone.src.main.home.Restaurant.RestaurantRecyclerViewAdapter
import com.example.coupangeats_clone.src.main.home.api.*
import com.example.coupangeats_clone.src.main.search.SearchKeywordActivity
import com.example.coupangeats_clone.src.models.CategoryResponse
import com.example.coupangeats_clone.src.models.GetAdResponse
import com.example.coupangeats_clone.src.models.GetRepresentAddressResponse

import com.example.coupangeats_clone.src.models.Model.CategoryModel
import com.example.coupangeats_clone.src.models.Model.RestaurantModel
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.coupangeats_clone.src.models.RestaurantResponse
import com.example.template_practice.config.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    GetRestaurantView, GetCategoryView, GetRepresentAddressView,GetAdView {

    // autoscrollview 사진
    var data = ArrayList<Int>()

    // category list
    var categorylist = ArrayList<CategoryModel>()

    // category list Adapter
    lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    // small store list
    var smallStoreList = ArrayList<StoreModel>()

    // category list Adapter
    lateinit var smallStoreAdapter: SmallStoreRecyclerViewAdapter

    // store list
    var storeList = ArrayList<StoreModel>()

    // category list Adapter
    lateinit var storeAdapter: StoreRecyclerViewAdapter

    //
    var restaurantList = ArrayList<RestaurantModel>()
    lateinit var restaurantAdapter:RestaurantRecyclerViewAdapter

    //
    var adList = ArrayList<String>()

    var SET:Int = 1 // 주소 설정을 위한 변수

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 주소 설정
        binding.homeTopAppBar.setOnClickListener {
            val intent = Intent(context, SetAddressActivity::class.java)
            startActivityForResult(intent, SET)
        }

//        GetRepresentAddressService(this).tryGetRepresentAddress(
//            sSharedPreferences.getString(
//                X_ACCESS_TOKEN, ""
//            )!!
//        )




        // auto scroll view 연결
        connectAutoScrollView()
        //GetAdService(this).tryGetAd()

        // 카테고리 사진 리스트에 넣기
        addCategoryList()

        // 카테고리 정보 받아오기, 리스트에 넣기
        GetCategoryService(this).tryGetCategory()

        // category adapter 인스턴스 생성
        categoryAdapter = CategoryRecyclerViewAdapter()
        categoryAdapter.submitList(categorylist)


        binding.homeRecyclerviewCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerviewCategory.adapter = categoryAdapter


        // small store list data
        addSmallStoreList()


        // small store adater 연결
        smallStoreAdapter = SmallStoreRecyclerViewAdapter()
        smallStoreAdapter.submitList(smallStoreList)
        binding.homeRecyclerviewPopularStore.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.homeRecyclerviewPopularStore.adapter = smallStoreAdapter

        // click listener
        smallStoreAdapter.setItemClickListener(object :
            SmallStoreRecyclerViewAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                //showCustomToast("${position} 클릭")
                val intent = Intent(context, DetailStoreActivity::class.java)
                intent.putExtra("content", smallStoreList[position])
                startActivity(intent)
            }
        })



        // 가게 정보 받아오기
        //GetRestaurantService(this).tryGetRestaurants("골라먹는 맛집")

//        restaurantAdapter = RestaurantRecyclerViewAdapter()
//        restaurantAdapter.submitList(restaurantList)
//        binding.homeRecyclerviewStore.layoutManager = LinearLayoutManager(context)
//        binding.homeRecyclerviewStore.adapter = restaurantAdapter

        // store list data
        addStoreList()

        // store adapter 연결
        storeAdapter = StoreRecyclerViewAdapter()
        storeAdapter.submitList(storeList)
        binding.homeRecyclerviewStore.layoutManager = LinearLayoutManager(context)
        binding.homeRecyclerviewStore.adapter = storeAdapter


        // click listener
        storeAdapter.setItemClickListener(object :
            StoreRecyclerViewAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                //showCustomToast("${position} 클릭")
                val intent = Intent(context, DetailStoreActivity::class.java)
                intent.putExtra("content", storeList[position])
                startActivity(intent)
            }
        })


        // chip
        chipClickListener()

        binding.homeTopAppBar.setOnMenuItemClickListener {

            startActivity(Intent(context, SearchKeywordActivity::class.java))
            true

        }


    }

    // 주소 설정 받아오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SET){
            if (resultCode == RESULT_OK){
                var address = data?.extras?.getString("address","위치")
                binding.registerToolbarTitle.text = address
                }

            Snackbar.make(binding.root, "배달 주소가 변경되었습니다.", Snackbar.LENGTH_SHORT)

        }

    }


    fun chipClickListener() {
        changeChipColor(binding.homeChip1)
        changeChipColor(binding.homeChip2)
        changeChipColor(binding.homeChip3)
        changeChipColor(binding.homeChip4)
        changeChipColor(binding.homeChip5)

    }

    fun changeChipColor(chip: Chip) {
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                chip.setChipBackgroundColorResource(R.color.filter_blue)
                chip.setTextColor(resources.getColor(R.color.white, null))
            } else {
                chip.setChipBackgroundColorResource(R.color.white)
                chip.setTextColor(resources.getColor(R.color.black, null))
            }
        }
    }

    fun connectAutoScrollView() {
        data.add(R.drawable.view_page_1)
        data.add(R.drawable.view_page_2)
        data.add(R.drawable.view_page_3)
        data.add(R.drawable.view_page_4)
        data.add(R.drawable.view_page_5)
        data.add(R.drawable.view_page_6)

        val autoScrollAdapter = context?.let { AutoScrollAdapter(it, data) }
        //val autoScrollAdapter = context?.let { AutoScrollAdapter(it, adList) }
        binding.homeViewPager.adapter = autoScrollAdapter
        binding.homeViewPager.setInterval(3000)
        binding.homeViewPager.startAutoScroll()
    }

    fun addCategoryList() {
        categorylist.add(CategoryModel(R.drawable.category_new, "신규 맛집"))
        categorylist.add(CategoryModel(R.drawable.category_one, "1인분"))
        categorylist.add(CategoryModel(R.drawable.category_korean, "한식"))
        categorylist.add(CategoryModel(R.drawable.category_chicken, "치킨"))
        categorylist.add(CategoryModel(R.drawable.category_tteokbokki, "분식"))
        categorylist.add(CategoryModel(R.drawable.category_pork_cutlet, "돈까스"))
        categorylist.add(CategoryModel(R.drawable.category_jokbal, "족발/보쌈"))
        categorylist.add(CategoryModel(R.drawable.category_ttang, "찜/탕"))
        categorylist.add(CategoryModel(R.drawable.category_roast, "구이"))
        categorylist.add(CategoryModel(R.drawable.category_pizza, "피자"))
        categorylist.add(CategoryModel(R.drawable.category_chinese, "중식"))
        categorylist.add(CategoryModel(R.drawable.category_japanese, "일식"))
        categorylist.add(CategoryModel(R.drawable.category_sushi, "회/해물"))
        categorylist.add(CategoryModel(R.drawable.category_pasta, "양식"))
        categorylist.add(CategoryModel(R.drawable.category_coffee, "커피/차"))
        categorylist.add(CategoryModel(R.drawable.category_dessert, "디저트"))
        categorylist.add(CategoryModel(R.drawable.category_snack, "간식"))
        categorylist.add(CategoryModel(R.drawable.category_assian, "아시안"))
        categorylist.add(CategoryModel(R.drawable.category_sandwich, "샌드위치"))
        categorylist.add(CategoryModel(R.drawable.category_salad, "샐러드"))
        categorylist.add(CategoryModel(R.drawable.category_burger, "버거"))
        categorylist.add(CategoryModel(R.drawable.category_mexican, "멕시칸"))
        categorylist.add(CategoryModel(R.drawable.category_lunch_box, "도시락"))
        categorylist.add(CategoryModel(R.drawable.category_jook, "죽"))
        categorylist.add(CategoryModel(R.drawable.category_franchise, "프랜차이즈"))

    }

    fun addSmallStoreList() {
        for (i in 1..3) {
            smallStoreList.add(
                StoreModel(
                    R.drawable.popular_image2,
                    title = "굽네치킨 신정4동점",
                    star = "4.5",
                    review = "(267)",
                    distance = "1.0km",
                    delivery_fee = "무료배달",
                    time = "32~42분",
                    least_fee = "15,000원"
                )
            )
            smallStoreList.add(
                StoreModel(
                    R.drawable.detail_macaron,
                    title = "달달한날",
                    star = "4.8",
                    review = "(265)",
                    distance = "0.9km",
                    delivery_fee = "2,000원",
                    time = "10~19분",
                    chita = R.drawable.chita,
                    least_fee = "12,000원"
                )
            )
            smallStoreList.add(
                StoreModel(
                    R.drawable.store_image2_1,
                    title = "버거킹 신정네거리점",
                    star = "4.6",
                    review = "(1,389)",
                    distance = "0.7km",
                    delivery_fee = "무료배달",
                    time = "21~31분",
                    chita = R.drawable.chita,
                    least_fee = "12,000원"
                )
            )
            smallStoreList.add(
                StoreModel(
                    R.drawable.popular_image1,
                    title = "피자헛 신월점",
                    star = "4.7",
                    review = "(79)",
                    distance = "0.8km",
                    delivery_fee = "무료배달"
                )
            )
            smallStoreList.add(
                StoreModel(
                    R.drawable.category_tteokbokki,
                    title = "몬스터 곱창떡볶이 강서화곡점",
                    star = "5.0",
                    review = "(10)",
                    distance = "3.5km",
                    delivery_fee = "3,000원"
                )
            )
            smallStoreList.add(
                StoreModel(
                    R.drawable.category_one,
                    title = "응답해라 옛날도시락",
                    star = "4.6",
                    review = "(72)",
                    distance = "3.9km",
                    delivery_fee = "5,000원"
                )
            )
        }


    }

    fun addStoreList() {
        for (i in 1..10) {
            storeList.add(
                StoreModel(
                    R.drawable.store_image_1,
                    R.drawable.store_image_2,
                    R.drawable.store_image_3,
                    "진참치",
                    "4.8",
                    "(2,907)",
                    "0.8km",
                    "무료배달",
                    "20-30분",
                    R.drawable.chita
                )
            )
            storeList.add(
                StoreModel(
                    R.drawable.store_image2_1,
                    R.drawable.store_image2_2,
                    R.drawable.store_image2_3,
                    "버거킹 신정네거리점",
                    "4.6",
                    "(1,389)",
                    "0.7km",
                    "무료배달",
                    "29-39분"
                )
            )

            storeList.add(
                StoreModel(
                    R.drawable.store_image3_1,
                    R.drawable.store_image3_2,
                    R.drawable.store_image3_3,
                    "신촌갈비국밥 목동화곡점",
                    "4.7",
                    "(1,718)",
                    "2.0km",
                    "무료배달",
                    "14-24분"
                )
            )
            storeList.add(
                StoreModel(
                    R.drawable.store_image4_1,
                    R.drawable.store_image4_1,
                    R.drawable.store_image4_1,
                    "곱창닭도리탕&소곱창전골",
                    "5.0",
                    "(1)",
                    "2.0km",
                    "2,000원",
                    "17-27분",
                    R.drawable.chita
                )
            )

            storeList.add(
                StoreModel(
                    R.drawable.category_chicken,
                    R.drawable.category_chicken,
                    R.drawable.category_chicken,
                    "치킨선생 목동점",
                    "4.7",
                    "(323)",
                    "2.0km",
                    "무료배달",
                    "35-45분"
                )
            )

            storeList.add(
                StoreModel(
                    R.drawable.category_tteokbokki,
                    R.drawable.category_tteokbokki,
                    R.drawable.category_tteokbokki,
                    "몬스터 곱창떡볶이 강서화곡점",
                    "5.0",
                    "(10)",
                    "3.5km",
                    "3,000원",
                    "42-52분"
                )
            )

        }
    }


    override fun onGetCategorySuccess(response: CategoryResponse) {

        if (response.code == 1000) {
            for (itm in response.result) {
                //categorylist.add(CategoryModel(itm.CategoryImage,itm.CategoryName))
            }
        } else {
            response.message?.let { showCustomToast(it) }
        }

        // categoryApiAdapter.submitList(category_api_list)
    }

    override fun onGetCategoryFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetRestaurantSuccess(response: RestaurantResponse) {

        var imgList:List<String>

        if(response.code == 1000){
            for (item in response.result){
                imgList = item.image.split(",")
                val restaurant = RestaurantModel(imgList[0],"","",item.name,"${item.score}개","${item.reviewNum}","1.8km","3000원","${item.deliveryTime}분")
                restaurantList.add(restaurant)
            }
        }
        else{
            response.message?.let { showCustomToast(it) }
            Log.d(TAG,"HomeFragment - onGetRestaurantSuccess() called")
        }
    }

    override fun onGetRestaurantFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetRepresentAddressSuccess(response: GetRepresentAddressResponse) {
        if (response.code == 1000) {
            binding.registerToolbarTitle.text = response.result.address
        } else {
            //showCustomToast(response.message!!)
        }
    }

    override fun onGetRepresentFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetAdSuccess(response: GetAdResponse) {

        if(response.code == 1000){
            for (item in response.result){
                adList.add(item.adImage)
                connectAutoScrollView()
            }
        }
        else{
            showCustomToast(response.message!!)
        }
        Log.d(TAG,"HomeFragment - onGetAdSuccess() called - ${adList}")
    }

    override fun onGetAdFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}