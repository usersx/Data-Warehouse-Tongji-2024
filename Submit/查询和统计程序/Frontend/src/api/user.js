// 这里不需要真正的API请求了，所以可以注释掉或删除这些导入
// import request from '@/utils/request';

export function login(data) {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({ data: { token: 'mock-token' } });
    }, 1000);
  });
}

export function getInfo(token) {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve({
        data: {
          roles: ['admin'],
          introduction: 'I am a super administrator',
          avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
          name: 'Super Admin'
        }
      });
    }, 1000);
  });
}

export function logout() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve();
    }, 1000);
  });
}
