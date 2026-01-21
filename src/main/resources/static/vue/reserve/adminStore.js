const {defineStore} = Pinia

const useAdminStore = defineStore('admin', {
	state: () => ({
		reserve_list: []
	}),
	
	actions: {
		async dataRecv() {
			const res = await api.get('/admin/reserve_list_vue/')
			this.reserve_list = res.data
		},
		
		async reserveOk(no, id) {
			const res = await api.get('/admin/reserve_ok_vue', {
				params: {
					no: no,
					id: id
				}
			})
			
			this.reserve_list = res.data
		},
		
		async reserveDelete(no) {
			const res = await api.get('/admin/reserve_delete_vue', {
				params: {
					no: no
				}
			})
			
			this.reserve_list = res.data
		}
	}
})