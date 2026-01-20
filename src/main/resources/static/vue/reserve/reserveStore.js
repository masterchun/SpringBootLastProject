const {defineStore} = Pinia

const useReserveStore = defineStore('reserve', {
	state: () => ({
		loc_list: 		[
		  "강남구","강동구","강북구","강서구",
		  "관악구","광진구","구로구","금천구",
		  "노원구","도봉구","동대문구","동작구",
		  "마포구","서대문구","서초구","성동구",
		  "성북구","송파구","양천구","영등포구",
		  "용산구","은평구","종로구","중구","중랑구"
		],
		image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxZYeqBNbv7SJ03D1_R5yEnMeDZbX_0CIlCQ&s',
		loc: 'all',
		curpage: 1,
		totalpage: 0,
		food_list: [],
		title: '',
		cno: 0,
		day: '',
		time: '',
		inwon: '',
		isDate: true,
		isTime: false,
		isInwon: false,
		isReserveBtn: false,
		
		time_list: [], // 랜덤
		inwon_list: ["2명", "3명", "4명", "5명", "6명", "7명", "8명", "9명", "단체"],
		
	}),
	
	actions: {
		async dataRecv() {
			const res = await api.get('/reserve/food_list_vue', {
				params: {
					address: this.loc,
					page: this.curpage
				}
			})
			this.food_list = res.data.list
			this.loc = res.data.loc
			this.curpage = res.data.curpage
			this.totalpage = res.data.totalpage
		},
		
		dateSelect(day) {
			this.day = day
		},
		
		prev() {
			this.curpage = this.curpage > 1 ? this.curpage - 1 : this.curpage
			this.dataRecv()
		},
		
		next() {
		this.curpage = this.curpage < this.totalpage ? this.curpage + 1 : this.curpage
		this.dataRecv()
		},
		
		locChange() {
			this.curpage = 1
			this.dataRecv()
		},
		
		foodSelect(cno, title, image) {
			this.cno = cno
			this.title = title
			this.image = image
			this.isDate = true
		},
		
		timeSelect(time) {
			this.time = time
			this.isInwon = true
		},
		
		async timeListData() {
			const res = await api.get('/reserve/time_list-vue/')
			this.time_list = res.data.list
		},

		inwonSelect(inwon) {
			this.inwon = inwon
			this.isReserveBtn = true
		},
		
		async reserveBtn() {
			const res = await api.post('/reserve/insert_vue', {
				cno: this.cno,
				rday: this.day,
				rtime: this.time,
				rinwon: this.inwon
			})
			
			if (res.data === 'YES') {
				location.href = '/mypage/mypage_reserve'
			} else {
				alert('예약 실패')
				this.title = ''
				this.image = 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxZYeqBNbv7SJ03D1_R5yEnMeDZbX_0CIlCQ&s'
				this.time = ''
				this.inwon = ''
				this.isDate = true
				this.isTime = false
				this.isInwon = false
				this.isReserveBtn = false
			}
		}
	}
})