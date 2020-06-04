<template>
  <div>
    <!-- <div th:replace="Common/sidebar::sidebar"></div> -->
        <!-- <div th:replace="Common/header::header"></div> -->
    <div class="content-wrap">
      <div class="main">
        <div class="container-fluid">
          <section id="main-content">
            <div class="row">
              <div class="col-lg-12">
                <div class="card">
                  <div class="card-title">
                    <h1 style="text-align:center">校友录一览</h1>
                  </div>
                  <br/>
                  <div class="form-group">
                    <label>查询字段</label>
                    <select id="searchName" v-model="searchName" class="form-control"  style="width: 20%; display: inline">
                      <option>id</option>
                      <option>姓名</option>
                      <option>年级</option>
                      <option>学院</option>
                      <option>专业</option>
                    </select>
                    <label>请输入搜索内容</label>
                    <input id="searchText" name="searchText" v-model="searchText" type="text" class="form-control" placeholder="" width="0.3" style="width: 40%; display: inline">
                    <a href="javascript:void(0)" @click="getAlumni" type="button" class="btn btn-primary btn-flat btn-addon m-b-10 m-l-5" style="width: 10%; display: inline"><i class="ti-plus"></i>查询</a>
                    <a href="javascript:void(0)" @click="$router.push('/alumni/add')" type="button" class="btn btn-primary btn-flat btn-addon m-b-10 m-l-5" style="width: 10%; display: inline"><i class="ti-plus"></i>添加名单</a>
                  </div>
                  <br/>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table class="table table-hover">
                        <thead>
                        <tr>
                          <td style="text-align:center">id</td>
                          <td style="text-align:center">姓名</td>
                          <td style="text-align:center">性别</td>
                          <td style="text-align:center">年级</td>
                          <td style="text-align:center">学院</td>
                          <td style="text-align:center">专业</td>
                          <td style="text-align:center">电话</td>
                          <td style="text-align:center">邮箱</td>
                          <td style="text-align:center">查看信息</td>
                          <td style="text-align:center">删除成员</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="(alumni, index) in alumniList" :key="index">
                          <td style="text-align:center">{{alumni.id}}</td>
                          <td style="text-align:center">{{alumni.name}}</td>
                          <td style="text-align:center">{{alumni.gender}}</td>
                          <td style="text-align:center">{{alumni.grade}}</td>
                          <td style="text-align:center">{{alumni.college}}</td>
                          <td style="text-align:center">{{alumni.discipline}}</td>
                          <td style="text-align:center">{{alumni.phone}}</td>
                          <td style="text-align:center">{{alumni.email}}</td>
                          <td style="text-align:center">
                            <a href="javascript:void(0)" @click="$router.push({path: '/alumni/info',query:{id: alumni.id}})">
                              <i class="fa fa-bookmark fa-lg"></i>
                            </a>
                          </td>
                          <td style="text-align:center">
                            <a href="javascript:void(0)" @click="MyDelete(alumni)">
                              <i class="fa fa-trash fa-lg"></i>
                            </a>
                          </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- <div th:replace="Common/footer::footer"></div> -->
          </section>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  inject: ['reload'],
  data () {
    return {
      alumniList: [],
      searchName: '',
      searchText: ''
    }
  },
  created () {
    this.$axios.get('/api/alumni', {
      params: {
        searchName: '',
        searchText: ''
      }})
      .then((response) => {
        if (response.data.errno === 0) {
          this.alumniList = response.data.data
        } else {
          let str = '发生了某些不知名的错误...'
          this.$swal({
            title: '提交异常！',
            text: str,
            type: 'error'
          })
        }
      })
      .catch((error) => {
        console.log(error)// 打印服务端返回的数据(调试用)
        let str = '发生了某些不知名的错误...\n' + error
        this.$swal({
          title: '提交异常！',
          text: str,
          type: 'error'
        })
      })
  },
  methods: {
    getAlumni () {
      this.$axios.get('/api/alumni', {
        params: {
          searchName: this.searchName,
          searchText: this.searchText
        }})
        .then((response) => {
          if (response.data.errno === 0) {
            this.alumniList = response.data.data
          } else {
            let str = '发生了某些不知名的错误...'
            this.$swal({
              title: '提交异常！',
              text: str,
              type: 'error'
            })
          }
        })
        .catch((error) => {
          console.log(error)// 打印服务端返回的数据(调试用)
          let str = '发生了某些不知名的错误...\n' + error
          this.$swal({
            title: '提交异常！',
            text: str,
            type: 'error'
          })
        })
    },
    MyDelete (alumni) {
      this.$swal({
        title: '确定要删除吗？',
        text: alumni.id,
        type: 'warning',
        showCancelButton: true,
        cancelButtonText: '取消',
        confirmButtonColor: '#DD6B55',
        confirmButtonText: '确定'
      })
        .then((isConfirm) => {
          console.log(isConfirm)
          if (isConfirm.value == null) {
            return 0
          }
          this.$axios.delete('/api/alumni', {
            params: {
              id: alumni.id
            }})
            .then((response) => {
              this.$swal({
                title: '成功',
                text: '删除id为 ' + alumni.id + ' 的成员成功！',
                type: 'success'
              })
                .then(() => {
                  this.reload()
                })
            })
            .catch((error) => {
              console.log(error)// 打印服务端返回的数据(调试用)
              let str = '发生了某些不知名的错误...\n' + error
              this.$swal({
                title: '提交异常！',
                text: str,
                type: 'error'
              })
            })
        })
    }
  }
}
</script>

<style scoped>
  @import '../assets/css/lib/font-awesome.min.css';
  @import '../assets/css/lib/themify-icons.css';
  @import '../assets/css/lib/menubar/sidebar.css';
  @import '../assets/css/lib/bootstrap.min.css';
  @import '../assets/css/lib/helper.css';
  @import '../assets/css/style.css';
  @import '../assets/css/lib/sweetalert/sweetalert.css';
</style>
