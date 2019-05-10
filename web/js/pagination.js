/**
 * Created by user on 2017/6/24.
 */

class Pagination {

  constructor(photos, photoCapacity = 10, paginationCapacity = 5) {
    this.photos = photos;
    this.setPhotoCapacity(photoCapacity);
    this.setPaginationCapacity(paginationCapacity);
    this.currentPage = 0;
  }

  setPhotoCapacity(capacity) {
    this.photoCapacity = capacity;
  }
  setPaginationCapacity(capacity) {
    this.paginationCapacity = capacity;
  }


  upperDivide(a, b) {
      return parseInt((a - 1) / b + 1);
  }
  getPaginationNumber() {
      let tot = this.photos.length;
      return this.upperDivide(tot, this.photoCapacity);
  }

  fixPageNumber(pageNumber = 1) {
    let tot = this.getPaginationNumber();
    if (pageNumber < 1) {
      return 1;
    } else if (pageNumber > tot) {
      return tot;
    } else {
      return pageNumber;
    }
  }

  getPaginataion(pageNumber = 1) {
      this.currentPage = this.fixPageNumber(pageNumber);
      // console.log(this.currentPage);
      let getType = (page) => {
          if (typeof page === 'number') {
              if (this.currentPage === page) {
                  return 2;
              } else {
                  return 0;
              }
          } else {
              return 1;
          }
      };

      let res = [];
      let tot = this.getPaginationNumber();
      let startPage = this.currentPage - parseInt(this.paginationCapacity/ 2);
      startPage = this.fixPageNumber(startPage);
      let endPage = startPage + this.paginationCapacity;
      endPage = this.fixPageNumber(endPage);

      res.push({ idx: '\u00ab', type: 0}); // type: 0 normal    1 ...    2  active

      /// =====
      if (startPage >= 2) {
          res.push({idx: 1, type: getType(1)});
      }
      if (startPage >= 3) {
          res.push({idx: '...', type: getType('...')});
      }
      for (let i = startPage; i < endPage; i++) {
          res.push({idx: i, type: getType(i)});
      }
      if (endPage <= tot - 1) {
          res.push({idx: '...', type: getType('...')});
      }
      if (endPage <= tot) {
          res.push({idx: tot, type: getType(tot)});
      }
      /// =====

      res.push({idx: '\u00bb', type: 0});

      return this.pagination = res;
  }

  getPhotos(pageNumber = 1) {
      pageNumber = this.fixPageNumber(pageNumber);
      let start = (pageNumber - 1) * this.photoCapacity;
      let end = start + this.photoCapacity;
      let res = [];
      for (let i = start; i < end; i++) {
          if (this.photos[i]) {
              res.push(this.photos[i]);
          }
      }
      return this.shownPhotos = res;
  }

  show(pageNumber = 1, appendPhoto, appendPagination) {
      let res = this.getPaginataion(pageNumber);
      for (let i = 0; i < res.length; i++) {
          appendPagination(res[i]);
      }

      res = this.getPhotos(pageNumber);
      for (let i = 0; i < res.length; i++) {
          appendPhoto(res[i]);
      }
  }
  next(appendPhoto, appendPagination) {
      this.show(this.currentPage + 1, appendPhoto, appendPagination);
  }
  prev(appendPhoto, appendPagination) {
      this.show(this.currentPage - 1, appendPhoto, appendPagination)
  }
}

