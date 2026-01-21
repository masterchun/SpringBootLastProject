<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
const ID = '${sessionScope.userid}'
</script>
</head>
<body>
	<table class="table">
		<tr>
			<td class="text-center"><h3>예약 목록</h3></td>
		</tr>
	</table>
	<div id="reserveApp">
		<table class="table">
			<thead>
				<tr class="table-danger">
					<th class="text-center">예약번호</th>
					<th class="text-center">업체명</th>
					<th></th>
					<th class="text-center">예약일</th>
					<th class="text-center">예약시간</th>
					<th class="text-center">인원</th>
					<th class="text-center">등록일</th>
					<th></th>
				</tr>
				<tr v-for="(vo, index) in store.reserve_list" :key="index">
					<td class="text-center">{{ vo.no}}</td>
					<td>{{ vo.svo.title }}</td>
					<td class="text-center"><img :src="vo.svo.image1"
						style="width: 30px; height: 30px;"></td>
					<td class="text-center">{{ vo.rday }}</td>
					<td class="text-center">{{ vo.rtime }}</td>
					<td class="text-center">{{ vo.rinwon }}</td>
					<td class="text-center">{{ vo.dbday }}</td>
					<td class="text-center">
						<button class="btn-xs btn-info" v-if="vo.isReserve == 0">예약
							대기</button>
						<button class="btn-xs btn-success" @click="store.reserveDetail(vo.no)" v-else>예약 완료</button>
						<button class="btn-xs btn-warning" style="margin-left: 2px"
							v-if="vo.iscancel === 0" @click="store.reserveRequest(vo.no)">취소
							요청</button> <span class="btn-xs btn-default" style="margin-left: 2px"
						v-else>취소 대기</span>
					</td>
				</tr>
			</thead>
		</table>
		<div v-if="store.isShow">
		<table class="table">
			
			<tbody>
				<tr>
					<th colspan="8"><h3>예약 정보</h3></th>
				</tr>
				<tr>
					<th class="text-center">예약 번호</th>
					<td class="text-center">{{ store.rvo.no }}</td>
					<th class="text-center">예약일</th>
					<td class="text-center">{{ store.rvo.rday }}</td>
					<th class="text-center">예약 시간</th>
					<td class="text-center">{{ store.rvo.rtime }}</td>
					<th class="text-center">예약 인원</th>
					<td class="text-center">{{ store.rvo.rinwon }}</td>
				</tr>
			</tbody>
		</table>
		<table class="table">
			<tbody>
				<tr>
					<th><h3>맛집 정보</h3></th>
				</tr>
				<tr>
					<td width=30% class="text-center" rowspan="8"><img
						:src="store.rvo.svo.image1" style="width: 100%; height: 320px"></td>
					<td colspan="2"><h3>{{store.rvo.svo.title}}</h3></td>
				</tr>
				<tr>
					<td width="15%" class="text-center">주소</td>
					<td width="55%">{{store.rvo.svo.address }}</td>
				</tr>
				<tr>
					<td colspan="2" class="text-right">
						<button class="btn-sm btn-warning" @click="store.isShow = !store.isShow">
							닫기
						</button>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<div class="toast-container position-fixed bottom-0 end-0 p-3">
			<div id="reserveToast" class="toast">
				<div class="toast-header bg-success text-white">
					<strong class="me-auto">예약 알림</strong>
					<button type="button" class="btn-close" data-bs-dismission="toast"></button>
				</div>
				<div class="toast-body" id="toastMsg">
				
				</div>
			</div>
		</div>
	</div>
	<script src="/vue/axios.js"></script>
	<script src="/vue/reserve/mypageStore.js"></script>
	<script>
	const {createApp, onMounted} = Vue
	const {createPinia} = Pinia
	
	const app = createApp({
		setup() {
			const store = useMypageStore()
			
			onMounted(() => {
				store.dataRecv()
				store.connect(ID)
			})
			
			return {
				store
			}
		}
	})
	
	app.use(createPinia())
	app.mount("#reserveApp")
	</script>
</body>
</html>