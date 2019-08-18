package com.example.moviedb.page.landing;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.moviedb.constants.PublicConstants;
import com.example.moviedb.datamodel.MovieItem;
import com.example.moviedb.datamodel.landing.MovieResponse;
import com.example.moviedb.provider.MovieProvider;
import com.example.moviedb.rule.RxSchedulerRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by kevin.adhitama on 2019-08-17.
 */
public class LandingViewModelUnitTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Rule
    public RxSchedulerRule rxSchedulerRule = new RxSchedulerRule();

    @Mock
    MovieProvider movieProvider;

    @Captor
    private ArgumentCaptor<Map<String, String>> argumentCaptor;

    MovieResponse res;
    LandingViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        res = createMockResponse();
        viewModel = new LandingViewModel();
    }

    @Test
    public void fetchDataWithCorrectRequest() {
        viewModel.onCreateViewModel(movieProvider);
        when(movieProvider.getMovieList(argumentCaptor.capture())).thenReturn(Observable.just(res));
        viewModel.fetchData(PublicConstants.FILTER_HIGHEST_RATING);

        Assert.assertEquals(argumentCaptor.getValue().get("sort_by"), PublicConstants.FILTER_HIGHEST_RATING);
        Assert.assertEquals(argumentCaptor.getValue().get("page"), "1");
    }

    @Test
    public void fetchDataWithoutParamsWithCorrectRequest() {
        viewModel.onCreateViewModel(movieProvider);
        when(movieProvider.getMovieList(argumentCaptor.capture())).thenReturn(Observable.just(res));
        viewModel.fetchData();

        Assert.assertEquals(argumentCaptor.getValue().get("sort_by"), PublicConstants.FILTER_MOST_POPULAR);
        Assert.assertEquals(argumentCaptor.getValue().get("page"), "1");
    }

    @Test
    public void fetchDataWithCorrectResult() {
        viewModel.onCreateViewModel(movieProvider);
        when(movieProvider.getMovieList(any())).thenReturn(Observable.just(res));
        viewModel.fetchData();

        List<MovieItem> movieItems = viewModel.movieList.getValue();
        Assert.assertNotNull(movieItems);
        for (int i = 0; i < movieItems.size(); i++) {
            MovieItem response = res.getResults().get(i);
            MovieItem actualData = movieItems.get(i);

            Assert.assertEquals(response.getAdult(), actualData.getAdult());
            Assert.assertEquals(response.getVideo(), actualData.getVideo());
            Assert.assertEquals(response.getBackdropPath(), actualData.getBackdropPath());
            Assert.assertEquals(response.getFullBackdropPath(), actualData.getFullBackdropPath());
            Assert.assertEquals(response.getFullPosterPath(), actualData.getFullPosterPath());
            Assert.assertEquals(response.getGenreIds(), actualData.getGenreIds());
            Assert.assertEquals(response.getId(), actualData.getId());
            Assert.assertEquals(response.getOriginalLanguage(), actualData.getOriginalLanguage());
            Assert.assertEquals(response.getOriginalTitle(), actualData.getOriginalTitle());
            Assert.assertEquals(response.getOverview(), actualData.getOverview());
            Assert.assertEquals(response.getPopularity(), actualData.getPopularity());
            Assert.assertEquals(response.getPosterPath(), actualData.getPosterPath());
            Assert.assertEquals(response.getReleaseDate(), actualData.getReleaseDate());
            Assert.assertEquals(response.getTitle(), actualData.getTitle());
            Assert.assertEquals(response.getVoteAverage(), actualData.getVoteAverage());
            Assert.assertEquals(response.getVoteAverageDisplay(), actualData.getVoteAverageDisplay());
            Assert.assertEquals(response.getVoteCount(), actualData.getVoteCount());
        }
    }

    @Test
    public void fetchDataError() {
        viewModel.onCreateViewModel(movieProvider);
        when(movieProvider.getMovieList(any())).thenReturn(Observable.error(Exception::new));
        viewModel.fetchData();
    }

    @Test
    public void fetchDataDispose() {
        viewModel.onCreateViewModel(movieProvider);
        when(movieProvider.getMovieList(any())).thenReturn(Observable.just(res));
        viewModel.fetchData();
        viewModel.fetchData();
    }

    private MovieResponse createMockResponse() {
        int page = 1;
        int total_result = 1;
        int total_pages = 2;
        List<MovieItem> items = new ArrayList<>();
        items.add(new MovieItem(1, 1, false, 10f, "Test Movie", 1d, "", "/dummyPath", "en", "Test Movie", new ArrayList<>(), "", false, "Testing Movie is here", "10-10-2019"));

        return new MovieResponse(page, total_pages, total_pages, items);
    }
}
