<template>
    <div id="index-app">
        <!-- <sideHeader></sideHeader> -->
        <!-- <sideBar></sideBar> -->
        <div class="content-wrap">
            <div class="main">
                <div class="container-fluid">
                    <section id="main-content">
                      <ve-line :data="gradeData"></ve-line>

                        <!-- /# row -->
                        <!-- <sideFooter></sideFooter> -->
                    </section>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      res: null,
      gradeData: {
        columns: ['年级', '人数'],
        rows: []
      },
      disciplineData: {
        columns: ['专业', '人数'],
        rows: []
      }
    }
  },
  mounted () {
    this.countByGrade()
    // this.countByDiscipline()
  },
  methods: {
    countByGrade () {
      this.$axios.get('/api/alumni/cbg')
        .then((response) => {
          if (response.data.errno === 0) {
            var index = 0
            for (this.res in response.data.data) {
              var data = {'年级': response.data.data[index].col, '人数': response.data.data[index].count}
              this.gradeData.rows.push(data)
              index = index + 1
            }
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
    countByDiscipline () {
      this.$axios.get('/api/alumni/cbd')
        .then((response) => {
          if (response.data.errno === 0) {
            for (var index = 0; ; index++) {
              if (response.data.data[index] === null) {
                break
              }
              var data = {'专业': response.data.data[index].col, '人数': response.data.data[index].count}
              this.gradeData.rows.push(data)
            }
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
</style>
