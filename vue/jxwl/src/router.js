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
		path: '/adminindex',
		name: 'adminindex',
		component: () => import('./adminindex.vue'),
			children: [
				{
						path: '/',
						name: '',
						component: () => import('./components/shipperInformation/select.vue')
				},
			  {
			  	path: '/adminindex/adminSelect',
			  	name: 'adminSelect',
			  	component: () => import('./components/admin/select.vue')
			  },{
			  	path: '/adminindex/collectionFeeSelect',
			  	name: 'collectionFeeSelect',
			  	component: () => import('./components/collectionFee/select.vue')
			  },{
			  	path: '/adminindex/driverInformationSelect',
			  	name: 'driverInformationSelect',
			  	component: () => import('./components/driverInformation/select.vue')
			  },{
			  	path: '/adminindex/employeeInformationSelect',
			  	name: 'employeeInformationSelect',
			  	component: () => import('./components/employeeInformation/select.vue')
			  },{
			  	path: '/adminindex/evaluationInformationSelect',
			  	name: 'evaluationInformationSelect',
			  	component: () => import('./components/evaluationInformation/select.vue')
			  },{
			  	path: '/adminindex/noticeSelect',
			  	name: 'noticeSelect',
			  	component: () => import('./components/notice/select.vue')
			  },{
			  	path: '/adminindex/shipperInformationSelect',
			  	name: 'shipperInformationSelect',
			  	component: () => import('./components/shipperInformation/select.vue')
			  },{
			  	path: '/adminindex/scollectSelect',
			  	name: 'scollectSelect',
			  	component: () => import('./components/scollect/select.vue')
			  },{
			  	path: '/adminindex/vehicleSelect',
			  	name: 'vehicleSelect',
			  	component: () => import('./components/vehicle/select.vue')
			  },
		  ]
	  }
  ]
})
