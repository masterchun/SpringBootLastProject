const { defineStore } = Pinia

const intialStore = () => ({
	list: [],
	curpage: 1,
	totalpage: 0,
	startPage: 0,
	endPage: 0,
	fd: '해수욕장',
	selected: 12
})

const useJejuStore = defineStore('jeju_find', {
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
		async jejuFindData() {
			const res = await api.get('/jeju/find_vue/', {
				params: {
					page: this.curpage,
					selected: this.selected,
					fd: this.fd
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
			this.selected = data.selected
			this.fd = data.fd
		},
		
		movePage(page) {
			this.curpage = page
			this.jejuFindData()
		},
		
		find(findRef) {
			if(this.fd === '') {
				findRef?.focus()
				return
			}
			
			this.curpage = 1
			this.jejuFindData()
		}
	}
})