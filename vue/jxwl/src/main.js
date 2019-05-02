import Vue from 'vue'

import './plugins/axios'
import App from './main.vue'
import './plugins/iview.js'
import router from './router'
//引入高德地图控件
import VueAMap from 'vue-amap';

import Echarts from 'echarts'
Vue.prototype.echarts = Echarts
Vue.use(Echarts)
//高德地图web端api
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
key:'4df2ef1b5bc8e355279425166fbe866c',
plugin: [
	'AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar',
	'AMap.Map','AMap.MouseTool','AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor','AMap.Geocoder','Geocoder',
	'AMap.Pixel','AMap.Geolocation','AMap.Marker'
	],
uiVersion: "1.0"
});
Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
