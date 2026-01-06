const { defineStore } = Pinia

const intialStore = () => ({
	list: [],
	curpage: 1,
	totalpage: 0,
	startPage: 0,
	endPage: 0,
	address: '강서구',
})

const useBusanStore = defineStore('busan_find', {
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
		async busanFindData() {
			const res = await api.get('/busan/find_vue/', {
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
			this.busanFindData()
		},
		
		find(addressRef) {
			if(this.address === '') {
				addressRef?.focus()
				return
			}
			
			this.curpage = 1
			this.busanFindData()
		}
	}
})