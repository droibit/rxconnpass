package com.github.droibit.rxconnpass.app.ui.interactor

import com.github.droibit.rxconnpass.Event
import com.github.droibit.rxconnpass.app.model.SearchAction
import com.github.droibit.rxconnpass.app.model.exception.NetworkDisconnectedException
import com.github.droibit.rxconnpass.app.ui.view.EventListView
import com.github.droibit.rxconnpass.app.util.RxSchedulersOverrideRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import rx.Observable
import rx.functions.Action0
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription

class EventListInteractorTest {

    @Rule
    @JvmField
    val mockito = MockitoJUnit.rule()
    @Rule
    @JvmField
    val overrideSchedulers = RxSchedulersOverrideRule()

    @Mock
    lateinit var searchAction: SearchAction
    @Mock
    lateinit var view: EventListView
    @Mock
    lateinit var mockEvents: List<Event>
    @Mock
    lateinit var hideProgress: Action0
    @Mock
    lateinit var showProgress: Action0
    @Mock
    lateinit var hideRefreshProgress: Action0
    @Mock
    lateinit var showContent: Action1<List<Event>>
    @Mock
    lateinit var showError: Action1<Throwable>

    lateinit var interactor: EventListInteractor

    @Before
    fun setup() {
        interactor = EventListInteractor(action = searchAction,
                                         compositeSubscription = CompositeSubscription())
        interactor.init(view)

        `when`(view.showProgress()).thenReturn(showProgress)
        `when`(view.hideProgress()).thenReturn(hideProgress)
        `when`(view.hideRefreshProgress()).thenReturn(hideRefreshProgress)
        `when`(view.showContent()).thenReturn(showContent)
        `when`(view.showError()).thenReturn(showError)
    }

    @After
    fun tearDown() {
        interactor.onDetach()
    }

    @Test
    fun searchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(Observable.just(mockEvents))

        interactor.searchByKeyword("kotlin")

        verify(showProgress, times(1)).call()
        verify(hideProgress, times(1)).call()
        verify(showContent, times(1)).call(eq(mockEvents))
        verify(hideRefreshProgress, never()).call()
        verify(showError, never()).call(any())
    }

    @Test
    fun researchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(Observable.just(mockEvents))
        `when`(searchAction.research()).thenReturn(Observable.just(mockEvents))

        interactor.searchByKeyword("kotlin")
        interactor.researchByKeyword()

        verify(showProgress, times(1)).call()
        verify(hideProgress, times(1)).call()
        verify(showContent, times(2)).call(eq(mockEvents))
        verify(hideRefreshProgress, times(1)).call()
        verify(showError, never()).call(any())
    }

    @Test
    fun occurErrorInSearchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(Observable.error(NetworkDisconnectedException()))

        interactor.searchByKeyword("kotlin")

        verify(showProgress, times(1)).call()
        verify(hideProgress, never()).call()
        verify(showContent, never()).call(any())
        verify(hideRefreshProgress, never()).call()
        verify(showError, times(1)).call(isA(NetworkDisconnectedException::class.java))
    }

    @Test
    fun occurErrorInResearchByKeyword() {
        `when`(searchAction.search(anyString())).thenReturn(Observable.just(mockEvents))
        `when`(searchAction.research()).thenReturn(Observable.error(NetworkDisconnectedException()))

        interactor.searchByKeyword("kotlin")
        interactor.researchByKeyword()

        verify(showProgress, times(1)).call()
        verify(hideProgress, times(1)).call()
        verify(showContent, times(1)).call(any())
        verify(hideRefreshProgress, never()).call()
        verify(showError, times(1)).call(isA(NetworkDisconnectedException::class.java))
    }
}