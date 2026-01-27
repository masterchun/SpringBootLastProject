const {defineStore} = Pinia

const useMypageStore = defineStore('mypage', {
	state: () => ({
		reserve_list: [],
		rvo: {},
		isShow: false,
		stomp: null
	}),
	
	actions: {
		connect(id){
			const sock=new SockJS("/ws")
			this.stomp=Stomp.over(sock)
			
			this.stomp.connect({},()=>{
				this.stomp.subscribe('/sub/notice/'+id,(msg)=>{
					this.showToast(msg.body)
					this.dataRecv()
				})
			})
		},
		
		showToast(message){
			  const toast = document.getElementById("reserveToast")
			  const toastMsg = document.getElementById("toastMsg")

			  toastMsg.innerText = message;
			  toast.classList.add("show");

			  // 3초 후 자동 닫힘
			  
			  setTimeout(() => {
			     hideToast()
			  }, 5000);
			},
		
		async dataRecv() {
			const res = await api.get('/mypage/reserve_list_vue/')
			this.reserve_list = res.data
		},
		
		async reserveRequest(no) {
			const res = await api.get('/mypage/reserve_cancel_vue', {
				params: {
					no: no
				}
			})
			
			this.reserve_list = res.data
		},
		
		async reserveDetail(no) {
			const res = await api.get('/mypage/reserve_detail_vue', {
				params: {
					no: no
				}
			})
			this.rvo = res.data
			this.isShow = true
		}
	}
})

function hideToast() {
	const toast = document.getElementById("reserveToast");
	toast.classList.remove("show");
}