const { defineStore } = Pinia

const intialStore = () => ({
	list: [],
	curpage: 1,
	totalpage: 0,
	startPage: 0,
	endPage: 0,
	address: '마포',
})

const useSeoulStore = defineStore('seoul_find', {
	state: intialStore,
	
	getters: {
		range: (state) => {
			const arr = []
			
			for(let i = state.startPage; i <= state.endPage; i++) {
				arr.push(i)
			}
			
			return arr
		}
	},
	
	actions: {
		async seoulFindData() {
			const res = await api.get('/seoul/find_vue/', {
				params: {
					page: this.curpage,
					address: this.address
				}
			})
			
			this.setPageData(res.data)
		},
		
		setPageData(data) {
			this.list = data.list
			this.curpage = data.curpage
			this.totalpage = data.totalpage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.address = data.address
		},
		
		movePage(page) {
			this.curpage = page
			this.seoulFindData()
		},
		
		find(addressRef) {
			if(this.address === '') {
				addressRef?.focus()
				return
			}
			
			this.curpage = 1
			this.seoulFindData()
		}
	}
})