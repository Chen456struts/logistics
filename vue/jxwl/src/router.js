import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes: [{
		path: '/',
		name: '',
		component: () => import('./login.vue')
	}, {
		path: '/admin/',
		name: 'adminLogin',
		component: () => import('./adminlogin.vue')
	},  {
		path: '/sj/',
		name: 'sj',
		component: () => import('./czlogin.vue')
	},  {
		path: '/hz/',
		name: 'hz',
		component: () => import('./khlogin.vue')
	}, {
		path: '/adminindex',
		name: 'adminindex',
		component: () => import('./adminindex.vue'),
		children: [{
				path: '/',
				name: '',
				component: () => import('./views/adminindex.vue')
			},
			{
				path: '/adminindex/adminSelect',
				name: 'adminSelect',
				component: () => import('./components/admin/select.vue')
			}, {
				path: '/adminindex/collectionFeeSelect',
				name: 'collectionFeeSelect',
				component: () => import('./components/collectionFee/select.vue')
			}, {
				path: '/adminindex/driverInformationSelect',
				name: 'driverInformationSelect',
				component: () => import('./components/driverInformation/select.vue')
			}, {
				path: '/adminindex/employeeInformationSelect',
				name: 'employeeInformationSelect',
				component: () => import('./components/employeeInformation/select.vue')
			}, {
				path: '/adminindex/evaluationInformationSelect',
				name: 'evaluationInformationSelect',
				component: () => import('./components/evaluationInformation/select.vue')
			}, {
				path: '/adminindex/noticeSelect',
				name: 'noticeSelect',
				component: () => import('./components/notice/select.vue')
			}, {
				path: '/adminindex/shipperInformationSelect',
				name: 'shipperInformationSelect',
				component: () => import('./components/shipperInformation/select.vue')
			}, {
				path: '/adminindex/scollectSelect',
				name: 'scollectSelect',
				component: () => import('./components/scollect/select.vue')
			}, {
				path: '/adminindex/vehicleSelect',
				name: 'vehicleSelect',
				component: () => import('./components/vehicle/select.vue')
			}, {
				path: '/adminindex/vehicleInfoSelect',
				name: 'vehicleInfoSelect',
				component: () => import('./components/vehicleInfo/select.vue')
			}, {
				path: '/adminindex/gdtest',
				name: 'gdtest',
				component: () => import('./components/test/gd.vue')
			}, {
				path: '/adminindex/orderInformationSelect',
				name: 'orderInformationSelect',
				component: () => import('./components/orderInformation/select.vue')
			}, {
				path: '/adminindex/orderInformationSelectysz',
				name: 'orderInformationSelectysz',
				component: () => import('./components/orderInformation/selectysz.vue')
			}, {
				path: '/adminindex/orderInformationSelectywc',
				name: 'orderInformationSelectywc',
				component: () => import('./components/orderInformation/selectywc.vue')
			}, {
				path: '/adminindex/orderInformationSelectdys',
				name: 'orderInformationSelectdys',
				component: () => import('./components/orderInformation/selectdys.vue')
			},
			{
				path: '/adminindex/financeInfoSelect',
				name: 'financeInfoSelect',
				component: () => import('./components/financeInfo/select.vue')
			},
			{
				path: '/adminindex/financeInfoSelectcz',
				name: 'financeInfoSelectcz',
				component: () => import('./components/financeInfo/selectcz.vue')
			}, {
				path: '/adminindex/financeInfoSelecttx',
				name: 'financeInfoSelecttx',
				component: () => import('./components/financeInfo/selecttx.vue')
			}, {
				path: '/adminindex/ddtj',
				name: 'ddtj',
				component: () => import('./components/baobiao/ddtj.vue')
			}, {
				path: '/adminindex/khcztj',
				name: 'khcztj',
				component: () => import('./components/baobiao/khcztj.vue')
			}, {
				path: '/adminindex/sjcztj',
				name: 'sjcztj',
				component: () => import('./components/baobiao/sjcztj.vue')
			},
		]
	}, {
		path: '/khindex',
		name: 'khindex',
		component: () => import('./khindex.vue'),
		children: [{
			path: '/',
			name: '',
			component: () => import('./views/khindex.vue')
		}, {
			path: '/khindex/ksxd',
			name: 'ksxd',
			component: () => import('./componentskh/xdgl/ksxd.vue')
		}, {
			path: '/khindex/ddglSelect',
			name: 'ddglSelect',
			component: () => import('./componentskh/ddgl/select.vue')
		}, {
			path: '/khindex/ddglSelectdys',
			name: 'ddglSelectdys',
			component: () => import('./componentskh/ddgl/selectdys.vue')
		}, {
			path: '/khindex/ddglSelectywc',
			name: 'ddglSelectywc',
			component: () => import('./componentskh/ddgl/selectywc.vue')
		}, {
			path: '/khindex/ddglSelectysz',
			name: 'ddglSelectysz',
			component: () => import('./componentskh/ddgl/selectysz.vue')
		}, {
			path: '/grzl/wdxx',
			name: 'ddglwdxx',
			component: () => import('./componentskh/grzl/wdxx.vue')
		}, {
			path: '/grzl/wdyk',
			name: 'ddglwdyk',
			component: () => import('./componentskh/grzl/wdyk.vue')
		}, {
			path: '/grzl/xtsz',
			name: 'xtsz',
			component: () => import('./componentskh/grzl/xtsz.vue')
		}]
	}, {
		path: '/czindex',
		name: 'czindex',
		component: () => import('./czindex.vue'),
		children: [{
			path: '/',
			name: '',
			component: () => import('./views/czindex.vue')
		}, {
			path: '/czindex/wdcl',
			name: 'wdcl',
			component: () => import('./componentscz/grzl/wdcl.vue')
		}, {
			path: '/czindex/wdxx',
			name: 'wdxx',
			component: () => import('./componentscz/grzl/wdxx.vue')
		}, {
			path: '/czindex/xtsz',
			name: 'xtsz',
			component: () => import('./componentscz/grzl/xtsz.vue')
		}, {
			path: '/czindex/ddglSelect',
			name: 'ddglSelect',
			component: () => import('./componentscz/ddgl/select.vue')
		}, {
			path: '/czindex/ddglSelectysz',
			name: 'ddglSelectysz',
			component: () => import('./componentscz/ddgl/selectysz.vue')
		}, {
			path: '/czindex/ddglSelectywc',
			name: 'ddglSelectywc',
			component: () => import('./componentscz/ddgl/selectywc.vue')
		}, {
			path: '/czindex/ksjd',
			name: 'ksjd',
			component: () => import('./componentscz/jdkl/ksjd.vue')
		}, ]
	}, ]
})
