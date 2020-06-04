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
                    <h1 style="text-align:center">成员信息</h1>
                  </div>
                  <div class="card-body">
                    <div class="basic-elements">
                      <form id="form" method="put">
                        <div class="row">
                          <div class="col-lg-6">
                            <div class="form-group">
                              <label>ID</label>
                              <input id="id" name="id" v-model="id" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>姓名</label>
                              <input id="name" name="name" v-model="name" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>性别</label>
                              <select id="gender" v-model="gender" class="form-control">
                                <option>男</option>
                                <option>女</option>
                              </select>
                            </div>
                            <div class="form-group">
                              <label>年级</label>
                              <input id="grade" name="grade" v-model="grade" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>学院</label>
                              <input id="college" name="college" v-model="college" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>专业</label>
                              <input id="discipline" name="discipline" v-model="discipline" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>电话</label>
                              <input id="phone" name="phone" v-model="phone" type="text" class="form-control" placeholder="">
                            </div>
                            <div class="form-group">
                              <label>邮箱</label>
                              <input id="email" name="email" v-model="email" type="text" class="form-control" placeholder="">
                            </div>
                            <br/>
                            <button id="button" type="button" class="btn btn-primary btn-rounded m-b-10 m-l-5" style="width:48%;float:right" @click="MyCheck"> 修改信息 </button>
                          </div>
                        </div>
                      </form>
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
      id: '',
      name: '',
      gender: '',
      grade: '',
      college: '',
      discipline: '',
      phone: '',
      email: ''
    }
  },
  mounted () {
    let alumniId = this.$route.query.id
    this.$axios.get('/api/alumni/info', {
      params: {
        id: alumniId
      }})
      .then((response) => {
        if (response.data.errno === 0) {
          this.id = response.data.data.id
          this.name = response.data.data.name
          this.gender = response.data.data.gender
          this.grade = response.data.data.grade
          this.college = response.data.data.college
          this.discipline = response.data.data.discipline
          this.phone = response.data.data.phone
          this.email = response.data.data.email
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
    MyError (str) {
      this.$swal({
        title: '提交异常！',
        text: str,
        type: 'error'
      })
    },
    MySubmit (str) {
      this.$swal({
        title: '确定要提交吗？',
        text: str,
        type: 'warning',
        showCancelButton: true,
        cancelButtonText: '取消',
        confirmButtonColor: '#DD6B55',
        confirmButtonText: '确定'
      })
        .then((isConfirm) => {
          if (isConfirm.value == null) {
            return 0
          }
          let alumniData = {
            id: this.id,
            name: this.name,
            gender: this.gender,
            grade: this.grade,
            college: this.college,
            discipline: this.discipline,
            phone: this.phone,
            email: this.email
          }
          this.$axios.put('/api/alumni', alumniData)
            .then((response) => {
              if (response.data.errno === 0) {
                let message = '修改 ' + this.id + ' 成员成功！'
                this.$swal({
                  title: '成功',
                  text: message,
                  type: 'success'
                })
                  .then(() => {
                    this.reload()
                  })
              } else {
                this.$swal({
                  title: '失败',
                  text: response.data.errmsg,
                  type: 'error'
                })
                  .then(() => {
                    this.reload()
                  })
              }
            })
            .catch((error) => {
              console.log(error)// 打印服务端返回的数据(调试用)
              let str = '发生了某些不知名的错误...\r' + error
              this.MyError(str)
            })
        })
    },
    MyCheck () {
      if (this.name === '') {
        let str = '姓名不能为空！'
        this.MyError(str)
        return 0
      }
      if (this.gender === '') {
        let str = '性别不能为空！'
        this.MyError(str)
        return 0
      }
      if (this.grade === '') {
        let str = '年级不能为空！'
        this.MyError(str)
        return 0
      }
      if (this.college === '') {
        let str = '学院不能为空！'
        this.MyError(str)
        return 0
      }
      if (this.discipline === '') {
        let str = '专业不能为空！'
        this.MyError(str)
        return 0
      }

      var str = '成员信息' +
        '\nID：' + this.id +
        '\n姓名：' + this.name +
        '\n性别：' + this.gender +
        '\n年级：' + this.grade +
        '\n学院：' + this.college +
        '\n专业：' + this.discipline +
        '\n电话：' + this.phone +
        '\n邮箱：' + this.email +

        // eslint-disable-next-line no-use-before-define
        this.MySubmit(str)
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
