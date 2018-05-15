import { overheidPage } from './app.po';

describe('overheid App', () => {
  let page: overheidPage;

  beforeEach(() => {
    page = new overheidPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
