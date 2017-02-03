package com.cartisan.modern.common.page;

import com.cartisan.modern.common.view.Params;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.cartisan.modern.common.builder.PageViewBuilder.builder;
import static com.cartisan.modern.common.page.CurrentPage.FIRST_PAGE;
import static com.cartisan.modern.common.page.PageView.PAGE_PARAM_NAME;
import static java.lang.String.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(NestedRunner.class)
public class PageViewTest {
    private PageView view;

    public class PageNumber {
        @Test
        public void param_page_exists() {
            view = builder().currentPage(5)
                    .currentPageMessage("Current page is %s")
                    .build();

            assertThat(view.getModelMap().get("currentPage")).isEqualTo("Current page is 5");
        }
    }

    public class PreviousPage {
        @Test
        public void previous_page_when_not_on_first_page() {
            view = builder().currentPage(2)                    .build();

            assertPreviousPageEquals(pageUrl(PAGE_PARAM_NAME, 1));
        }

        @Test
        public void previous_page_when_on_first_page() {
            view = builder().currentPage(FIRST_PAGE).build();
            assertPreviousPageEquals(null);
        }

        private void assertPreviousPageEquals(String previousPageUrl) {
            assertThat(view.getModelMap().get("previousPageUrl")).isEqualTo(previousPageUrl);
        }
    }

    public class NextPage {
        @Test
        public void next_page_when_not_on_last_page() {
            view = builder().currentPage(4).build();

            totalPageCountIs(5);

            assertNextPageUrlEquals(pageUrl(PAGE_PARAM_NAME, 5));
        }

        @Test
        public void next_page_when_on_last_page() {
            view = builder().currentPage(4).build();

            totalPageCountIs(4);

            assertThat(view.getModelMap().get("nextPageUrl")).isEqualTo(null);
        }

        private void assertNextPageUrlEquals(String expected) {
            assertThat(view.getModelMap().get("nextPageUrl")).isEqualTo(expected);
        }

        private void totalPageCountIs(int totalPageCount) {
            view.display(totalPageCount);
        }
    }

    private String pageUrl(String paramName, int paramValue) {
        Params pageUrl = new Params();
        pageUrl.add(paramName, valueOf(paramValue));
        return pageUrl.getQuery();
    }

}
